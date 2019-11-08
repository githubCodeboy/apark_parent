package com.apark.controller.testUser;


import com.alibaba.fastjson.JSON;
import com.apark.common.response.ServiceResponse;
import com.apark.constant.BaseController;
import com.apark.pojo.user.User;
import com.apark.redisCache.GetCache;
import com.apark.service.Interface.IRedisService;
import com.apark.service.impl.user.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@Slf4j
@RequestMapping("/apark/user")
public class UserController extends BaseController {

	
	@Autowired
	private UserService userService;

	/*@Autowired
	private RedisUtil  redisUtil;*/

    @Autowired
    private IRedisService redisService;





	//@RequestMapping("/getCardDetail")
	//@ResponseBody
	/*public String getCardDetail(Map params,String token) throws BusinessException {

		try {
			//String token = wXUtils.getAccess_token();
			String url ="https://api.weixin.qq.com/card/get?access_token="+token;
			Response response = okHttpUtil.postSyncJson(url, JSONObject.toJSONString(params));
			String res = response.body().string().replace("\\","");
			log.info("卡劵详情----{}",res);
			return res ;
			//return new ServiceResponse("200","获取卡劵详情成功",res);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}*/

	/*@RequestMapping("/getCardList")
	@ResponseBody
	public ServiceResponse getCardList(@RequestBody Map params) throws BusinessException {

		try {
			String token = wXUtils.getAccess_token();
			String url ="https://api.weixin.qq.com/card/user/getcardlist?access_token="+token;
			Response response = okHttpUtil.postSyncJson(url, JSONObject.toJSONString(params));
			String resJson = response.body().string().replace("\\","");
			Map res = JSONObject.parseObject(resJson);
			if(res.get("card_list")!=null){
				List<Map> card_list = JSONObject.parseArray(res.get("card_list").toString(),Map.class);
				for(Map item:card_list){
					Map cardDetailParams = new HashMap();
					cardDetailParams.put("card_id",item.get("card_id").toString());
					String resStr = getCardDetail(cardDetailParams,token);
					item.put("cardDetail",resStr);
				}
				return new ServiceResponse("200","获取卡劵列表成功",card_list);
			}
			return new ServiceResponse("200","卡包暂无卡劵");
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}*/

	@RequestMapping("/testFinishPayInfo")
	@ResponseBody
	public ServiceResponse finishPayInfo(@RequestBody Map params)  {
	/*	String token = this.tokenData.getToken();

		Map result = HISrequest.requestFromHIS("outpatient.getCompletedPayInfo",params,token);*/

		return new ServiceResponse("200","已缴费信息查询成功","");
	}

/*	@RequestMapping("/testQueryPayInfo")
	@ResponseBody
    public ServiceResponse testQueryPayInfo(@RequestBody Map params) throws BusinessException {
		String token = this.tokenData.getToken();

		Map result = HISrequest.requestFromHIS("outpatient.getPayInfo",params,token);

    	return new ServiceResponse("200","缴费信息查询成功",result);
	}*/

	/*@RequestMapping("/testQueryPayInfoDetail")
	@ResponseBody
	public ServiceResponse testQueryPayInfoDetail(@RequestBody Map params) throws BusinessException {
		String token = this.tokenData.getToken();

		Map result = HISrequest.requestFromHIS("outpatient.getPaybillfee",params,token);

		return new ServiceResponse("200","缴费信息查询成功",result);
	}

	@RequestMapping("/getPrescriptionInfo")
	@ResponseBody
	public ServiceResponse getPrescriptionInfo(@RequestBody Map params) throws BusinessException {
		String token = this.tokenData.getToken();

		Map result = HISrequest.requestFromHIS("outpatient.getPrescriptionInfo",params,token);

		return new ServiceResponse("200","处方记录查询成功",result);
	}

	@RequestMapping("/getPrescriptionDetailInfo")
	@ResponseBody
	public ServiceResponse getPrescriptionDetailInfo(@RequestBody Map params) throws BusinessException {
		String token = this.tokenData.getToken();

		Map result = HISrequest.requestFromHIS("outpatient.getPrescriptionDetailInfo",params,token);

		return new ServiceResponse("200","处方详情查询成功",result);
	}*/


	@RequestMapping("/testAdd")
	@ResponseBody
	public String   test(){
		User u = new User();
		u.setName("小明");
		u.setAge(20);
		this.userService.insertUser(u);

		User u2 = new User();
		u2.setName("小红");
		this.userService.insertUser(u2);

		return u.toString();
	}



	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	@ResponseBody
	public Object sessions (HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", request.getSession().getId());
		map.put("message", request.getSession().getAttribute("map"));
		return map;
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	/**
	 * 测试插入
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(long id,String name){
		User u = new User();
		u.setId(id);
		u.setName(name);
		this.userService.insertUser(u);
		return u.getId()+"    " + u.getName();
	}

    /**
     * 测试插入
     * @return
     */
    @RequestMapping("/addList")
    @ResponseBody
    public String addList(){
        for (int i = 15; i < 50 ; i++) {

            User u = new User();
            u.setId((long)i);
            u.setName("测试记录 " + i);
            this.userService.insertUser(u);
        }


        return "添加记录  完成   ！！！ ";
    }
	/**
	 * 测试读
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/{id}")
	@ResponseBody
	public String findById(@PathVariable("id") String id){
		User u = this.userService.findById(id);
		return u.getId()+"    " + u.getName();
	}
	/**
	 * 测试写入多条记录
	 * @return
	 */
	@RequestMapping("/addAndRead")
	@ResponseBody
	public String addAndRead(long id,String name){
		User u = new User();
		u.setId(id);
		u.setName(name);
		this.userService.wirteAndRead(u);
		return u.getId()+"    " + u.getName();
	}

    /**
     * 测试读取分页  数据
     * @return
     */

    @RequestMapping("/getPageList")
    @ResponseBody
	@GetCache(name="getPageList",value="id")
    public List<User>  getPageList(){

        PageHelper.startPage(1,10);
        List<User>   userList  = this.userService.getPageList();

        return userList;
    }

	/**
	 * 测试  genarate生成  实体类 mapper
	 * 读取分页  数据
	 * @return
	 */

	@RequestMapping("/getPageList_genarate")
	@ResponseBody
	public PageInfo getPageList_genarate(){

		//PageHelper.startPage(1,10);
		Page<User> page = PageHelper.startPage(1, 10);
		List<User>   userList  = this.userService.getPageList_genarate();
		PageInfo  pageInfo  = new PageInfo<User>(page) ;


		//page.setList(userList);
		return pageInfo;
	}



    /**
     * 测试读取不分页  数据
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public List<User>  getList(HttpServletResponse response){

		//response.setHeader("Access-Control-Allow-Origin","*");
        List<User>   userList  = this.userService.getPageList();

        return userList;
    }

	/**
	 * 测试读然后写
	 * @param id
	 * @param userName
	 * @return
	 */
	@RequestMapping("/readAndAdd")
	@ResponseBody

	public String readAndWrite(long id,String userName){
		User u = new User();
		u.setId(id);
		u.setName(userName);
		this.userService.readAndWirte(u);
		return u.getId()+"    " + u.getName();
	}

	/**
	 *  redis 缓存 测试读写
	 * @param key

	 * @return
	 */
	@RequestMapping("/rdTestRead")
	@ResponseBody
	public Object redisTest_read(String key){
		//Object value  = redisUtil.get("001");
        String name = redisService.get("001");
        System.out.println(name);
        return  name;
	}

	@RequestMapping("/rdGetkey")
	@ResponseBody
	public String redisTest_getkey(String key){
		//Object value  = redisUtil.get("001");
		String name = redisService.get(key);
		System.out.println(name);
		return "   " +   name;
	}

	/**
	 * 测试 阿里云 短信发送
	 * */
/*	@RequestMapping("/testSendmsm")
	@ResponseBody
	public String testSendmsm(String key){
		Map<String, Object> data =  new HashMap<>();
		data.put("code", "777877") ;
		SmsResponse smsResponse  =aliYunNewSmsSenderImpl.send(new SmsAccess(), "13710641142" ,data) ;

		return JSON.toJSONString(smsResponse);
	}*/
	/**
	 * 测试 中文乱码
	 * */
	@RequestMapping("/testCode")
	@ResponseBody
	public String testCode(String nickName){
		PageInfo pinfo = new PageInfo();
		pinfo.setSize(8);
		pinfo.setPageNum(1);
//		userQuestionServiceImpl.getUserQuestionList(6,1,pinfo );
		System.out.println(nickName);
		return JSON.toJSONString(nickName);
	}

	/**
	 * 测试 mongodb 数据库连接
	 * */
/*	@RequestMapping("/testMongoDB")
	@ResponseBody
	public String testMongoDB(String nickName){

		TestPerson person = new TestPerson();
		person.setId(78768686123123L);
		person.setAddress("番禺区钟村198号");
		person.setAge(23);
		person.setName("数据库");
		testPersonServiceImpl.saveTestPerson(person);

		return JSON.toJSONString(person);

	}*/


	@RequestMapping("/testList")
	@ResponseBody
	public String testList(String nickName){

		return   null  ;

	}

/*	@RequestMapping("/TestExemptLogin")
	@ResponseBody
	public String TestExemptLogin(){

		super.getRequest().getSession().removeAttribute(UserConstants.USER_LOGIN_WECHAT);

		log.info("清楚登录session"+super.getRequest().getSession().getAttribute(UserConstants.USER_LOGIN_WECHAT) );

		return "清楚登录session成功";

	}*/



	//测试查询  消息记录
/*
	@RequestMapping("/testMsg")
	@ResponseBody
	public String  testMsg(){

		InqueryMsgDataSpec queryModel = new InqueryMsgDataSpec();
		queryModel.setQuestionId(4);
		inqueryMsgDataService.getInqueryMsgDataList(queryModel,new com.ayhealth.utils.Page());

		return JSON.toJSONString("test");
	}
*/

	/**
	 *
	 *   程序给 区域表插入level值
	 */
/*	@RequestMapping("/insertlevel")
	@ResponseBody
	public String insertlevelForDistrict(){

		districtServiceImpl.setAutoLevel();
		return JSON.toJSONString("haha  we  win!");
	}*/
	
	/**
	 * 测试分页插件
	 * @return
	 */
	/*@RequestMapping("/queryPage")
	@ResponseBody
	public String queryPage(){
		PageInfo<User> page = this.userService.queryPage("tes", 1, 2);
		StringBuilder sb = new StringBuilder();
		sb.append("<br/>总页数=" + page.getPages());
		sb.append("<br/>总记录数=" + page.getTotal()) ;
		for(User u : page.getList()){
			sb.append("<br/>" + u.getId() + "      " + u.getName());
		}
		System.out.println("分页查询....\n" + sb.toString());
		return sb.toString();
	}*/
	
}
