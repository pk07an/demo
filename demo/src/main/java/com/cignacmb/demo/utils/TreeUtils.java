package com.cignacmb.demo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cignacmb.demo.common.TreeNode;

/**
 * 
 * @类名： TreeUtils.java 
 * @描述：树操作工具类
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
public class TreeUtils
{
    
    /**
     * 
     * 功能描述：构建菜单树,将节点集合中的子菜单放到父菜单的List中，并进行排序
     * @param nodeList
     * @return
     */
    public static List<TreeNode> buildMenuTree(List<TreeNode> nodeList)
    {
        //建立父节点ID和子节点列表的映射关系
        Map<Long, List<TreeNode>> parentMap = new HashMap<Long, List<TreeNode>>();
        for (TreeNode node : nodeList)
        {
            List<TreeNode> childList = parentMap.get(node.getParentId());
            if (childList == null)
            {
                childList = new ArrayList<TreeNode>();
                parentMap.put(node.getParentId(), childList);
            }
            childList.add(node);
        }

        return sortMenuOrder(0L, parentMap);
    }


    /**
     * 
     * 功能描述：菜单节点排序，同级别的菜单节点按大小顺序排序，递归调用进行子节点排序
     * @param parentId
     * @param parentMap
     * @return
     */
    private static List<TreeNode> sortMenuOrder(Long parentId, Map<Long, List<TreeNode>> parentMap)
    {
        List<TreeNode> childList = parentMap.get(parentId);
        if (childList == null || childList.isEmpty())
        {
            return null;
        }
        Comparator<TreeNode> comparator = new ComparatorByOrder();
        Collections.sort(childList, comparator);
        for (TreeNode treeNode : childList)
        {
            //递归调用，不断地找出节点的子节点进行排序
            List<TreeNode> children = sortMenuOrder(treeNode.getId(), parentMap);
            treeNode.setChildren(children);
        }
        return childList;
    }
    

}
