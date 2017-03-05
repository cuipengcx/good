/** 
 * Project Name:good-manage 
 * File Name:Project.java 
 * Package Name:com.jk.model 
 * Date:2017年2月7日下午3:16:20 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

/** 
 * ClassName:Project <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月7日 下午3:16:20 <br/> 
 * @author   JK 
 * @version   
 * @since    JDK 1.8 
 * @see       
 */
@Table(name="t_project")
public class Project extends BaseEntity {
	
	private  String projectName;//项目名称
	private  String projectPicture;//项目图片
	private  String  projectNumber;//项目编号     //唯一
	private  String projectLead;//项目导语
	private long industryId;  //  项目类别   关联类别表	
	private long  earnings ;  //预计收益人数
	private String projectPartner;//项目合作伙伴  一个项目最多有3个合作伙伴。   中间有;隔开。
	private Date  beginTime;//项目周期   开始日期
	private Date  endTime;//项目周期   结束日期
	private String incomeGroupDescription; //收益群体描述   ：直接收益人群的特征  80字
	private String projectRequiredmentAnalysis;//项目需求分析  ：发起项目的主要缘由 120字
	private long fullTimeNumber; //项目团队:全职人数
	private long  partTimeNumber;//项目团队:兼职人数
	private long  volunteerNumber;//项目团队:志愿者人数
	private String projectObjectives;//项目目标:介绍项目想要达到的目标 80字
	private String projectDesignRoadOne;  //项目设计路径1 80字   (必填)
	private String projectDesignRoadTwo;  //项目设计路径2 80字   (选填)
	private String projectDesignRoadThree;//项目设计路径3 80字    (选填) 
	private String expectedResultsOne; //预期成果1  (必填)
	private String expectedResultsTwo; //预期成果2  (选填)
	private String expectedResultsThree;//预期成果3  (选填)
	private int status;//项目状态(对项目而言)       0:未审核，1：审核失败（驳回） 2：审核成功(募款中) 3:终止项目(前台用户控制)     4:项目草稿
	private int  adminStatus;//项目状态(对后台管理员而言)    0:启用中(默认) 1:禁止中      后台项目禁止中，前台项目无法被搜寻
	private BigDecimal  score;//分数
	private  int  sort;//排序  用于首页进行排序	
	private String failReasion;//审核失败原因
	private int   projectType;//项目类型    1:个人项目   2：企业项目
	private int   isScore; //0:未打分  1：已打分
//	以下是 钱与项目用户id	 等属性
	private Long projectCapital;//项目需求资金                  - 精确到分
	private Long  raisedFunds;//项目已捐款资金   已筹资金  - 精确到分
	private Long  cashWithdrawal;//已提现资金		----精确到分  -20170221新增字段	
//  属性变动，只显示项目城市
	private long  cityId;  //关联城市表  t_area
//	private String  province; //项目地域---省/直辖市    使用插件,存取字符串          
//	private String  city;//项目地域---普通城市/直辖市       使用插件，存取字符串
//	private String address;//项目详细地址
	private long  goodUserId;  //  用户的Id
	private String userName; //用户名
	private String authenticationName;   //特别注意：该字段存放(机构/个人姓名查询)    根据项目的类别取对应的数据
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthenticationName() {
		return authenticationName;
	}
	public void setAuthenticationName(String authenticationName) {
		this.authenticationName = authenticationName;
	}
	public long getGoodUserId() {
		return goodUserId;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public void setGoodUserId(long goodUserId) {
		this.goodUserId = goodUserId;
	}
	public Long getProjectCapital() {
		return projectCapital;
	}
	public void setProjectCapital(Long projectCapital) {
		this.projectCapital = projectCapital;
	}
	public Long getRaisedFunds() {
		return raisedFunds;
	}
	public void setRaisedFunds(Long raisedFunds) {
		this.raisedFunds = raisedFunds;
	}
	public Long getCashWithdrawal() {
		return cashWithdrawal;
	}
	public void setCashWithdrawal(Long cashWithdrawal) {
		this.cashWithdrawal = cashWithdrawal;
	}
	public int getIsScore() {
		return isScore;
	}
	public void setIsScore(int isScore) {
		this.isScore = isScore;
	}
	public int getProjectType() {
		return projectType;
	}
	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectPicture() {
		return projectPicture;
	}
	public void setProjectPicture(String projectPicture) {
		this.projectPicture = projectPicture;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getProjectLead() {
		return projectLead;
	}
	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}
	public long getIndustryId() {
		return industryId;
	}
	public void setIndustryId(long industryId) {
		this.industryId = industryId;
	}
	public long getEarnings() {
		return earnings;
	}
	public void setEarnings(long earnings) {
		this.earnings = earnings;
	}
	public String getProjectPartner() {
		return projectPartner;
	}
	public void setProjectPartner(String projectPartner) {
		this.projectPartner = projectPartner;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getIncomeGroupDescription() {
		return incomeGroupDescription;
	}
	public void setIncomeGroupDescription(String incomeGroupDescription) {
		this.incomeGroupDescription = incomeGroupDescription;
	}
	public String getProjectRequiredmentAnalysis() {
		return projectRequiredmentAnalysis;
	}
	public void setProjectRequiredmentAnalysis(String projectRequiredmentAnalysis) {
		this.projectRequiredmentAnalysis = projectRequiredmentAnalysis;
	}
	public long getFullTimeNumber() {
		return fullTimeNumber;
	}
	public void setFullTimeNumber(long fullTimeNumber) {
		this.fullTimeNumber = fullTimeNumber;
	}
	public long getPartTimeNumber() {
		return partTimeNumber;
	}
	public void setPartTimeNumber(long partTimeNumber) {
		this.partTimeNumber = partTimeNumber;
	}
	public long getVolunteerNumber() {
		return volunteerNumber;
	}
	public void setVolunteerNumber(long volunteerNumber) {
		this.volunteerNumber = volunteerNumber;
	}
	public String getProjectObjectives() {
		return projectObjectives;
	}
	public void setProjectObjectives(String projectObjectives) {
		this.projectObjectives = projectObjectives;
	}
	public String getProjectDesignRoadOne() {
		return projectDesignRoadOne;
	}
	public void setProjectDesignRoadOne(String projectDesignRoadOne) {
		this.projectDesignRoadOne = projectDesignRoadOne;
	}
	public String getProjectDesignRoadTwo() {
		return projectDesignRoadTwo;
	}
	public void setProjectDesignRoadTwo(String projectDesignRoadTwo) {
		this.projectDesignRoadTwo = projectDesignRoadTwo;
	}
	public String getProjectDesignRoadThree() {
		return projectDesignRoadThree;
	}
	public void setProjectDesignRoadThree(String projectDesignRoadThree) {
		this.projectDesignRoadThree = projectDesignRoadThree;
	}
	public String getExpectedResultsOne() {
		return expectedResultsOne;
	}
	public void setExpectedResultsOne(String expectedResultsOne) {
		this.expectedResultsOne = expectedResultsOne;
	}
	public String getExpectedResultsTwo() {
		return expectedResultsTwo;
	}
	public void setExpectedResultsTwo(String expectedResultsTwo) {
		this.expectedResultsTwo = expectedResultsTwo;
	}
	public String getExpectedResultsThree() {
		return expectedResultsThree;
	}
	public void setExpectedResultsThree(String expectedResultsThree) {
		this.expectedResultsThree = expectedResultsThree;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(int adminStatus) {
		this.adminStatus = adminStatus;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getFailReasion() {
		return failReasion;
	}
	public void setFailReasion(String failReasion) {
		this.failReasion = failReasion;
	}
	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", projectPicture=" + projectPicture + ", projectNumber="
				+ projectNumber + ", projectLead=" + projectLead + ", industryId=" + industryId + ", earnings="
				+ earnings + ", projectPartner=" + projectPartner + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", incomeGroupDescription=" + incomeGroupDescription + ", projectRequiredmentAnalysis="
				+ projectRequiredmentAnalysis + ", fullTimeNumber=" + fullTimeNumber + ", partTimeNumber="
				+ partTimeNumber + ", volunteerNumber=" + volunteerNumber + ", projectObjectives=" + projectObjectives
				+ ", projectDesignRoadOne=" + projectDesignRoadOne + ", projectDesignRoadTwo=" + projectDesignRoadTwo
				+ ", projectDesignRoadThree=" + projectDesignRoadThree + ", expectedResultsOne=" + expectedResultsOne
				+ ", expectedResultsTwo=" + expectedResultsTwo + ", expectedResultsThree=" + expectedResultsThree
				+ ", status=" + status + ", adminStatus=" + adminStatus + ", score=" + score + ", sort=" + sort
				+ ", failReasion=" + failReasion + ", projectType=" + projectType + ", isScore=" + isScore
				+ ", projectCapital=" + projectCapital + ", raisedFunds=" + raisedFunds + ", cashWithdrawal="
				+ cashWithdrawal + ", goodUserId=" + goodUserId + ", cityId=" + cityId + "]";
	}

}
  