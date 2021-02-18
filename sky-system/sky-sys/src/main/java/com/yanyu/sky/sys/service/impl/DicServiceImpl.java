package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.util.AssertUtil;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.vo.dic.DicSaveVo;
import com.yanyu.sky.sys.bean.vo.dic.DicSearchVo;
import com.yanyu.sky.sys.bean.po.Dic;
import com.yanyu.sky.sys.bean.po.DicItem;
import com.yanyu.sky.sys.dao.DicItemMapper;
import com.yanyu.sky.sys.dao.DicMapper;
import com.yanyu.sky.sys.service.IDicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据字典 业务实现类
 * @author yanyu
 */
@Service
@Slf4j
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements IDicService {

    @Autowired
    private DicItemMapper dicItemMapper;

    @Override
    public IPage<Dic> listPDicPage(DicSearchVo vo) {
        return this.page(new Page<>(vo.getPageNum(), vo.getPageSize()), Wrappers.<Dic>lambdaQuery()
                .like(StringUtils.isNotBlank(vo.getCode()), Dic::getCode,vo.getCode())
                .like(StringUtils.isNotBlank(vo.getLabel()), Dic::getLabel,vo.getLabel())
                .orderByAsc(Dic::getSort));
    }

    @Override
    public void add(DicSaveVo vo) {
        canUpdate(null, vo.getCode());

        Dic dic = new Dic();
        BeanUtils.copyProperties(vo, dic);
        baseMapper.insert(dic);
    }

    @Override
    public void update(DicSaveVo vo) {
        canUpdate(vo.getId(), vo.getCode());

        Dic dic = new Dic();
        BeanUtils.copyProperties(vo, dic);
        baseMapper.updateById(dic);
    }

    @Override
    @Transactional
    public void delete(IdsEntity vo) {
        vo.getIds().stream().forEach(id -> {
            Dic dic = this.getById(id);
            baseMapper.deleteById(id);
            dicItemMapper.delete(Wrappers.<DicItem>lambdaQuery().eq(DicItem::getCode, dic.getCode()));
        });
    }

    private void canUpdate(String id, String code) {

        int cnt = 0;
        cnt = baseMapper.selectCount(Wrappers.<Dic>lambdaQuery()
                .ne(StringUtils.isNotBlank(id), Dic::getId, id)
                .eq(Dic::getCode, code));
        AssertUtil.isTrue(cnt <= 0, "编码已存在");
    }
}
