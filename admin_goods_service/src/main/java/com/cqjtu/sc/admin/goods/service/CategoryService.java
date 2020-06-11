package com.cqjtu.sc.admin.goods.service;

import com.cqjtu.sc.admin.goods.db.dao.AllGoodsCategoryMapper;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsCategory;
import com.cqjtu.sc.admin.goods.db.domain.AllGoodsCategoryExample;
import com.cqjtu.sc.admin.goods.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    AllGoodsCategoryMapper allGoodsCategoryMapper;

    /**
     * 根据板块id得到该板块下的所有的一级商品分类
     *
     * @return
     */
    public List<AllGoodsCategory> getGoodsL1Categories() {
        AllGoodsCategoryExample goodsCategoryExample = new AllGoodsCategoryExample();
//        AllSpflExample.Criteria criteria = goodsCategoryExample.createCriteria();
//        criteria.andFldjEqualTo(1);
        goodsCategoryExample.or().andCategoryLevelEqualTo(1);
        return allGoodsCategoryMapper.selectByExample(goodsCategoryExample);
    }

    /**
     * 得到二级分类
     *
     * @param superiorCategoryId
     * @return
     */
    public List<AllGoodsCategory> getGoodsL2CategoriesBySuperiorCategoryId(int superiorCategoryId) {
        AllGoodsCategoryExample goodsCategoryExample = new AllGoodsCategoryExample();
        AllGoodsCategoryExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andCategoryLevelEqualTo(2);
        criteria.andParentCategoryIdEqualTo(superiorCategoryId);
        return allGoodsCategoryMapper.selectByExample(goodsCategoryExample);
    }

    /**
     * 得到三级分类
     *
     * @param superiorCategoryId
     * @return
     */
    public List<AllGoodsCategory> getGoodsL3CategoriesBySuperiorCategoryId(int superiorCategoryId) {
        AllGoodsCategoryExample goodsCategoryExample = new AllGoodsCategoryExample();
        AllGoodsCategoryExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andCategoryLevelEqualTo(3);
        criteria.andParentCategoryIdEqualTo(superiorCategoryId);
        return allGoodsCategoryMapper.selectByExample(goodsCategoryExample);
    }

    /**
     * 根据分类id获得分类信息
     *
     * @param id
     * @return
     */
    public AllGoodsCategory getGoodsCategoryById(int id) {
        try {
            return allGoodsCategoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 添加分类
     *
     * @param allGoodsCategory 分类对象
     * @return 成功返回true, 失败返回false
     */
    public boolean addGoodsCategory(AllGoodsCategory allGoodsCategory) {
        try {
            allGoodsCategoryMapper.insertSelective(allGoodsCategory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据分类分级查询分类对象
     *
     * @param level 分类等级
     * @return 分类对象列表
     */
    public List<AllGoodsCategory> findByCategoryLevel(int level) {
        try {
            AllGoodsCategoryExample allSpflExample = new AllGoodsCategoryExample();
            allSpflExample.or().andCategoryLevelEqualTo(level);
            return allGoodsCategoryMapper.selectByExample(allSpflExample);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 根据父分类ID找分类对象
     *
     * @param superiorId 父分类ID
     * @return 分类对象
     */
    public List<AllGoodsCategory> findByCategorySuperiorId(int superiorId) {
        try {
            AllGoodsCategoryExample allSpflExample = new AllGoodsCategoryExample();
            allSpflExample.or().andCategoryLevelEqualTo(superiorId);
            return allGoodsCategoryMapper.selectByExample(allSpflExample);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得分类子类的深度（用于遍历所有的深度的子分类）
     *
     * @return 最深的分类数
     */
    public int getCategoryDepth() {
        try {
            int result;
            AllGoodsCategoryExample allSpflExample = new AllGoodsCategoryExample();
            allSpflExample.setDistinct(true);//去重
            result = allGoodsCategoryMapper.selectByExampleSelective(allSpflExample, AllGoodsCategory.Column.categoryLevel).size();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 按照id查找
     *
     * @param id
     * @return
     */
    public AllGoodsCategory findById(Integer id) {
        try {
            return allGoodsCategoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 按照父id查找
     *
     * @param id
     * @return
     */
    public String findSuperiorNameBySuperiorId(Integer id) {
        try {
            AllGoodsCategoryExample allSpflExample = new AllGoodsCategoryExample();
            allSpflExample.or().andParentCategoryIdEqualTo(id);
            return allGoodsCategoryMapper.selectByPrimaryKey(id).getCategoryName();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取父分类树节点
     *
     * @param id
     * @return
     */
    public String[] getCategoryTree(Integer id) {

        if (id == 0) return new String[]{"0"};

        AllGoodsCategory allSpfl = this.findById(id);
        int pid = allSpfl.getCategoryId();
        List<String> multi = new ArrayList<>();
        if (pid == 0) return new String[]{"0"};
        else {
            pid = id;
            multi.add(pid + "");
            do {
                pid = this.findById(pid).getCategoryId();
                multi.add(pid + "");

            } while (pid != 0);
        }

        Collections.reverse(multi);

        String[] res = new String[multi.size()];
        for (int i = 0; i < multi.size(); i++) {
            res[i] = multi.get(i);
        }

        return res;
    }

    /**
     * 根据主键更新
     *
     * @param allSpfl
     * @return
     */
    public boolean updateById(AllGoodsCategory allSpfl) {
        try {
            allGoodsCategoryMapper.updateByPrimaryKey(allSpfl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id) {
        try {
            allGoodsCategoryMapper.deleteByPrimaryKey(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllChildrenById(Integer id) {
        Integer[] ids = findAllChildrenId(id);
        try {
            for (Integer i : ids) {
                this.deleteById(i);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 找到所有的子节（包括自己）
     *
     * @param id
     * @return
     */
    public Integer[] findAllChildrenId(Integer id) {

        List<Integer> result = new ArrayList<>();
        result.add(id);

        List<AllGoodsCategory> allSpfls1 = this.findByCategorySuperiorId(id);
        for (AllGoodsCategory allSpfl1 : allSpfls1) {
            Integer id1 = allSpfl1.getCategoryId();
            result.add(id1);

            List<AllGoodsCategory> allSpfls2 = this.findByCategorySuperiorId(id1);
            for (AllGoodsCategory allSpfl2 : allSpfls2) {
                Integer id2 = allSpfl2.getCategoryId();

                result.add(id2);

                List<AllGoodsCategory> allSpfls3 = this.findByCategorySuperiorId(id2);
                for (AllGoodsCategory allSpfl3 : allSpfls3) {
                    Integer id3 = allSpfl3.getCategoryId();

                    result.add(id3);

                    List<AllGoodsCategory> allSpfls4 = this.findByCategorySuperiorId(id3);
                    for (AllGoodsCategory allSpfl4 : allSpfls4) {
                        Integer id4 = allSpfl4.getCategoryId();

                        result.add(id4);
                    }
                }
            }

        }

        Integer[] ids = new Integer[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ids[i] = result.get(i);
        }
        return ids;
    }


    /**
     * 检查是否到分类的子节点的分类名是否有重复
     *
     * @param id   父id
     * @param data 需要检查的分类名的字符串
     * @return 有重复返回true 没有重复返回false
     */
    public boolean isUnderTreeRepeatedById(Integer id, String data) {

        //一级分类的情况
        if (id == 0) {
            List<AllGoodsCategory> allSpfls = this.findByCategoryLevel(1);
            for (AllGoodsCategory allSpfl : allSpfls) {
                if (allSpfl.getCategoryName().equals(data)) {
                    return true;
                }
            }
            return false;
        } else {
            //二级以上分类的情况
            AllGoodsCategory allSpfl;
            try {
                allSpfl = this.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
            if (data.equals(allSpfl.getCategoryName())) {
                return true;
            }
            String tmpString;
            AllGoodsCategory tmp = allSpfl;
            while (true) {
                if (tmp.getCategoryId() == 0) {
                    return false;
                }
                tmp = this.findById(tmp.getCategoryId());
                tmpString = tmp.getCategoryName();
                if (tmpString.equals(data)) {
                    return true;
                }
            }
        }
    }

    public int mostDeepLevel(int categoryNum) {

        Integer[] temp = findAllChildrenId(categoryNum);

        List<AllGoodsCategory> temps = new ArrayList<>();
        for (Integer i : temp) {
            temps.add(this.findById(i));
        }

        int result = 0;
        for (AllGoodsCategory allSpfl : temps) {

            int t = allSpfl.getCategoryLevel();

            if (t > result) {
                result = t;
            }
        }

        return result;
    }

    /**
     * 获取所有的分类
     *
     * @return
     */
    public List<AllGoodsCategory> findAllGoodCategory() {

        AllGoodsCategoryExample allSpflExample = new AllGoodsCategoryExample();

        return allGoodsCategoryMapper.selectByExample(allSpflExample);

    }

    /**
     * solution 1 : just for 2 class category
     *
     * @return
     */
    public Object generateCategoryTree() {

        List<AllGoodsCategory> categorys = this.findAllGoodCategory();

        List<AllGoodsCategory> class1 = new ArrayList<>();
        List<AllGoodsCategory> class2 = new ArrayList<>();


        // identify the class
        for (AllGoodsCategory allSpfl : categorys) {
            if (allSpfl.getCategoryLevel() == 1) {
                class1.add(allSpfl);
            } else if (allSpfl.getCategoryLevel() == 2) {
                class2.add(allSpfl);
            } else {
                class1.add(allSpfl);
            }
        }

        List<CategoryVo> categoryVos1 = new ArrayList<>();
        for (AllGoodsCategory allSpfl1 : class1) {

            CategoryVo categoryVo1 = new CategoryVo();
            categoryVo1.AllspflConvertToCategoryVo(allSpfl1, null);
            List<CategoryVo> children = new ArrayList<>();

            for (AllGoodsCategory allSpfl2 : class2) {
                if (allSpfl1.getCategoryId().equals(allSpfl2.getCategoryId())) {
                    CategoryVo categoryVo2 = new CategoryVo();
                    categoryVo2.AllspflConvertToCategoryVo(allSpfl2, allSpfl1.getCategoryId().toString());
                    children.add(categoryVo2);
                }
            }
            categoryVo1.setChildren(children);
            categoryVos1.add(categoryVo1);
        }


        return categoryVos1;
    }


}
