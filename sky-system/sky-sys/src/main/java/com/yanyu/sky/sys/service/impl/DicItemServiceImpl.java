package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.util.AssertUtil;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.vo.dic.DicItemSaveVo;
import com.yanyu.sky.sys.bean.vo.dic.DicItemSearchVo;
import com.yanyu.sky.sys.bean.enums.IsDefaultType;
import com.yanyu.sky.sys.bean.po.DicItem;
import com.yanyu.sky.sys.dao.DicItemMapper;
import com.yanyu.sky.sys.service.IDicItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 数据字典项 业务实现类
 * @author yanyu
 */
@Service
public class DicItemServiceImpl extends ServiceImpl<DicItemMapper, DicItem> implements IDicItemService {

    @Override
    public IPage<DicItem> listCDicPage(DicItemSearchVo vo) {
        return this.page(new Page<>(vo.getPageNum(), vo.getPageSize()), Wrappers.<DicItem>lambdaQuery()
                .like(StringUtils.isNotBlank(vo.getValue()), DicItem::getValue,vo.getValue())
                .like(StringUtils.isNotBlank(vo.getName()), DicItem::getName,vo.getName())
                .eq(DicItem::getCode, vo.getCode()).orderByDesc(DicItem::getIsDefault).orderByAsc(DicItem::getSort));
    }

    @Override
    public void add(DicItemSaveVo vo) {
        canUpdate(vo.getId(), vo.getCode(), vo.getValue(), vo.getIsDefault());

        DicItem dicItem = new DicItem();
        BeanUtils.copyProperties(vo,dicItem);
        baseMapper.insert(dicItem);
    }
    @Override
    public void update(DicItemSaveVo vo) {
        canUpdate(vo.getId(),vo.getCode(), vo.getValue(), vo.getIsDefault());

        DicItem dicItem = new DicItem();
        BeanUtils.copyProperties(vo,dicItem);
        baseMapper.updateById(dicItem);
    }

    @Override
    public void delete(IdsEntity vo) {
        baseMapper.deleteBatchIds(vo.getIds());
    }

    private void canUpdate(String id, String code,String value, IsDefaultType defaultType) {
        int cnt = this.count(Wrappers.<DicItem>lambdaQuery().ne(StringUtils.isNotBlank(id),DicItem::getId,id).eq(DicItem::getValue,value).eq(DicItem::getCode, code));
        AssertUtil.isTrue(cnt <=0,"字典项重复");

        if(IsDefaultType.YES.equals(defaultType)) {
            cnt = this.count(Wrappers.<DicItem>lambdaQuery().ne(StringUtils.isNotBlank(id),DicItem::getId,id).eq(DicItem::getIsDefault,defaultType).eq(DicItem::getCode, code));
            AssertUtil.isTrue(cnt <=0,"默认值已经被设置");
        }
    }

}
