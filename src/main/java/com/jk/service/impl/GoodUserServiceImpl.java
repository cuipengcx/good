package com.jk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.GoodUserMapper;
import com.jk.model.GoodUser;
import com.jk.service.GoodUserService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/2/21.
 */
@Transactional
@Service
public class GoodUserServiceImpl extends BaseServiceImpl<GoodUser> implements GoodUserService{
    @Resource
    private GoodUserMapper goodUserMapper;

    @Transactional(readOnly=true)
    @Override
    public PageInfo<GoodUser> findPage(Integer pageNum, Integer pageSize, String username, String startTime, String endTime, Boolean isDel) throws Exception {
        Example example = new Example(GoodUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", isDel);

        if(StringUtils.isNotEmpty(username)){
            criteria.andLike("username", "%"+username+"%");
        }if(startTime != null && endTime != null){
            criteria.andBetween("createTime", DateUtil.beginOfDay(DateUtil.parse(startTime)), DateUtil.endOfDay(DateUtil.parse(endTime)));
        }

        //分页
        PageHelper.startPage(pageNum,pageSize);

        List<GoodUser> goodUserList = this.selectByExample(example);
        return new PageInfo<GoodUser>(goodUserList);
    }

    @Transactional(readOnly=true)
    @Override
    public GoodUser findUser(Long id, String username, String email, String mobilePhone, Boolean isLock, Boolean isDel, Boolean isEmailAuthed, Boolean isTeamAuthed, Boolean isPersonAuthed) throws Exception{
        GoodUser goodUser = new GoodUser();
        if(null != id){
            goodUser.setId(id);
        }
        if (StringUtils.isNotEmpty(username)) {
            goodUser.setUsername(username);
        }if (StringUtils.isNotEmpty(email)) {
            goodUser.setEmail(email);
        }if(StringUtils.isNotEmpty(mobilePhone)){
            goodUser.setMobilePhone(mobilePhone);
        }if(null != isLock){
            goodUser.setIsLock(isLock);
        }if(null != isDel){
            goodUser.setIsDel(isDel);
        }if(null != isEmailAuthed){
            goodUser.setIsEmailAuthed(isEmailAuthed);
        }if(null != isTeamAuthed){
            goodUser.setIsTeamAuthed(isTeamAuthed);
        }if(null != isTeamAuthed){
            goodUser.setIsPersonAuthed(isPersonAuthed);
        }
        return this.findOne(goodUser);
    }

    @Transactional(readOnly=true)
    @Override
    public List<GoodUser> findList(Long id, String username, String email, String mobilePhone, Boolean isLock, Boolean isDel, Boolean isEmailAuthed, Boolean isTeamAuthed, Boolean isPersonAuthed) throws Exception {
        GoodUser goodUser = new GoodUser();
        if(null != id){
            goodUser.setId(id);
        }
        if (StringUtils.isNotEmpty(username)) {
            goodUser.setUsername(username);
        }if (StringUtils.isNotEmpty(email)) {
            goodUser.setEmail(email);
        }if(StringUtils.isNotEmpty(mobilePhone)){
            goodUser.setMobilePhone(mobilePhone);
        }if(null != isLock){
            goodUser.setIsLock(isLock);
        }if(null != isDel){
            goodUser.setIsDel(isDel);
        }if(null != isEmailAuthed){
            goodUser.setIsEmailAuthed(isEmailAuthed);
        }if(null != isTeamAuthed){
            goodUser.setIsTeamAuthed(isTeamAuthed);
        }if(null != isTeamAuthed){
            goodUser.setIsPersonAuthed(isPersonAuthed);
        }
        return this.findListByWhere(goodUser);
    }

    @Transactional(readOnly=true)
    @Override
    public GoodUser findUserByEmail(String email) throws Exception {
        return this.findUser(null, null, email, null, null, false, null, null, null);
    }
}
