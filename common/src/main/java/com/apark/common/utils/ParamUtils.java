package com.apark.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class ParamUtils {

    /** 检查是否为手机号码(13开头的11位数字字串) */
    static Pattern compileIsMobileNumber = Pattern.compile("( *1[356789]\\d{9} *)");
    /**
     * 检查是否为手机号码
     * @param sMobile 被检查的字串
     * @return
     */
    public static boolean isMobileNumber(String sMobile) {
        if(sMobile == null) return false ;
        boolean flag = compileIsMobileNumber.matcher(sMobile).matches();
        return flag;
    }

    /**
     * 验证身份证号码的合法性
     * @param card (支持15位和18为的身份证号码)
     * @return  boolean (true为正确,false为不正确)
     */
   /* public static boolean isValidatedAllIdcard(String card) {
        if(card == null){
            return false;
        }
        String idcard = card.trim();
        if(idcard.length()!=15 && idcard.length()!=18){
            return false;
        }
        if (idcard.length() == 15) {
            //如果号码为15位,先把它转成18位再校验
            idcard = convertIdcarBy15bit(idcard);
        }
        return isValidate18Idcard(idcard);
    }


    *//**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard
     * @return
     *//*
    private static String convertIdcarBy15bit(String idcard) {
        String idcard17 = null;

        if (idcard.matches("^[0-9]*$")) {
            // 获取出生年月日
            String birthday = idcard.substring(6, 12);  //yyMMdd
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {

                e.printStackTrace();
            }
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char c[] = idcard17.toCharArray();
            String checkCode = "";

            if (null != c) {
                int bit[] = new int[idcard17.length()];

                // 将字符数组转为整型数组
                bit = converCharToInt(c);
                int sum17 = 0;
                sum17 = getPowerSum(bit);

                // 获取和值与11取模得到余数进行校验码
                checkCode = getCheckCodeBySum(sum17);
                // 获取不到校验位
                if (null == checkCode) {
                    return null;
                }

                // 将前17位与第18位校验码拼接
                idcard17 += checkCode;
            }
        } else { // 身份证包含数字
            return null;
        }
        return idcard17;
    }

    *//**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     *//*
    private static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    *//**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值 ,用于判断身份证号的最后一位是否正确
     *
     * @param bit
     * @return  int
     *//*
    private static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

*/

}
