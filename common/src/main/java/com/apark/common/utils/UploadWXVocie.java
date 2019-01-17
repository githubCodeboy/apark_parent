package com.apark.common.utils;


import com.apark.common.response.ServiceResponse;
import com.apark.common.utils.voiceTool.AudioOptions;
import com.apark.common.utils.voiceTool.FileInfo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
public class UploadWXVocie {

    private static OkHttpUtil okHttpUtil;

    static {
        okHttpUtil = new OkHttpUtil();
    }


    /**
     *  获取语音文件 .amr 转码成MP3
     * @param
     * @return  返回保存在七牛云的 文件名
     * @throws IOException
     */
    public static  String  uploadChangeCodeVoice_mp3( String wxToken , String serviceId) throws Exception {
        ServiceResponse serviceResponse;
        log.info(" 语音转码开始  ");
        //(1).  根据serverId 下载微信语音文件
        //1.1url
        String voiceUrl =  ""  ;     //OnlineChatConstants.WX_GET_FILE_URL;
        // 设置微信access_token  和资源id  media_id
        voiceUrl = voiceUrl.replace("$ACCESS_TOKEN",
                wxToken);
        voiceUrl = voiceUrl.replace("$MEDIA_ID",  serviceId);
        log.info(" 语音资源url :{}", voiceUrl);
        // 开始下载文件
        Request request = new Request.Builder()
                .url(voiceUrl)
                .build();

        Response response = okHttpUtil.mOkHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {

            Response res1 = null;
            FileInfo fileInfo = null;
            String tempOutputFile = null;
            try {
                String FileName = UUID.randomUUID().toString();
                res1 = response;
                log.info("a. 保存一份 amr 语音到硬盘 ");
                // a. 保存一份语音到硬盘
                //FileInfo fileInfo = FileUtil.saveFileByStream(response.body().byteStream(), UploadImgUtils.file_path, FileName , "amr") ;
                fileInfo = new FileInfo();
                fileInfo.setPath(UploadImgUtils.file_path);
                fileInfo.setFilename(FileName);
                fileInfo.setFileType("amr");
                downLoadFile(res1, UploadImgUtils.file_path, FileName + ".amr");

                tempOutputFile = fileInfo.getPath() + "\\" + fileInfo.getFilename() + "_out." + "mp3";
                String cmd_code = new AudioOptions().setSrc(fileInfo.getAbsFile())
                        .setDest(tempOutputFile)
                        .addOption("y", "") // 覆盖写
                        .addOption("write_xing", 0) // 解决mac/ios 显示音频时间不对的问题
                        .addOption("loglevel", "quiet") // 不输出日志
                        .build();
                log.info("  ==执行命令 :{}", cmd_code);
                log.info("b. 执行本地命令 转码mp3  ");
                //b. 执行本地命令 转码mp3
                Process process = Runtime.getRuntime().exec(cmd_code);
                try {
                    process.waitFor();
                    //休息一段时间
                    Thread.sleep(1500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String cfileneme = fileInfo.getFilename() + "_out." + "mp3";

                com.qiniu.http.Response res = UploadImgUtils.upload(tempOutputFile, cfileneme);
                // c. 文件地址存储到本地
                //上传失败
                if (res.error != null) {
                    log.info("======c.  微信语音转码上传失败  {} ", res.error);
                } else {

                    log.info("======c.  微信语音转码上传成功--{} ====", cfileneme);
                    return  cfileneme;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //删除本地 amr mp3语音文件
                try {
                    FileHelper.deleteLocalFile(fileInfo.getAbsFile());
                    FileHelper.deleteLocalFile(tempOutputFile);
                    res1 = null;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return   null;

    }



    public static  void   downLoadFile(Response res , String destFileDir ,String destFileName){
        log.info("  ");
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;

        //储存下载文件的目录
        File dir = new File(destFileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, destFileName);

        try {

            is = res.body().byteStream();
            long total = res.body().contentLength();
            fos = new FileOutputStream(file);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                //int progress = (int) (sum * 1.0f / total * 100);
                //下载中更新进度条
                // listener.onDownloading(progress);
            }
            fos.flush();
            //下载完成
            // listener.onDownloadSuccess(file);
        } catch (Exception e) {
            //.onDownloadFailed(e);
            log.error("下载保存文件出错== {}",e);
        }finally {

            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if(res != null){
                    res = null;
                }
            } catch (IOException e) {

            }

        }


    }
}
