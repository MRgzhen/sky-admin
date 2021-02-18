package com.yanyu.sky.sys.service;

import com.yanyu.sky.sys.bean.vo.basic.BasicPair;
import com.yanyu.sky.sys.bean.vo.basic.BasicPairNode;

import java.util.List;

/**
 * 下拉列表 业务接口
 * @author yanyu
 */
public interface IBasicService {

    List<BasicPairNode> listDept();

    List<BasicPair> listRole();

    List<BasicPair> listRoleByType(String type);

    List<BasicPairNode> listMenuByType(String type);

    List<BasicPairNode> listMenuWithPerm();

    List<BasicPair> listPDic();

    List<BasicPair> listChildDicByCode(String pCode);
}
