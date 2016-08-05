package com.cignacmb.demo.utils;

import java.util.Comparator;

import com.cignacmb.demo.common.TreeNode;

/**
 * 
 * @类名： ComparatorByOrder.java 
 * @描述：比较器，菜单根据节点进行排序，order值越大越靠前
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
public class ComparatorByOrder implements Comparator<TreeNode>
{
    
        public int compare(TreeNode node1, TreeNode node2)
        {
            return (int)(node1.getOrder() - node2.getOrder());
        }

}
