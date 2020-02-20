package hrds.k.biz.dbmtree.util;

import fd.ng.core.annotation.DocClass;
import fd.ng.core.annotation.Method;
import fd.ng.core.annotation.Param;
import fd.ng.core.annotation.Return;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DocClass(desc = "数据对标元管理-数据转树节点类", author = "BY-HLL", createdate = "2020/2/20 0020 下午 09:49")
public class DataConvertedNode {

    @Method(desc = "树数据转分叉树菜单数据", logicStep = "标准分类树数据转分叉树菜单数据")
    @Param(name = "dataList", desc = "数据列表", range = "dataList")
    @Return(desc = "转化成分叉树的树菜单数据字符串", range = "转化成分叉树的树菜单数据字符串")
    public static String DataConversionTreeInfo(List<Map<String, Object>> dataList) {
        // 节点列表（散列表，用于临时存储节点对象）
        Map<String, Node> nodeMap = new HashMap<>();
        // 根节点
        Node treeNodeData = new Node();
        // 根据结果集构造节点列表（存入散列表）
        dataList.forEach(dataRecord -> {
            Node node = new Node();
            node.id = (String) dataRecord.get("id");
            node.label = (String) dataRecord.get("label");
            node.parent_id = (String) dataRecord.get("parent_id");
            nodeMap.put(node.id, node);
        });
        // 构造无序的多叉树
        for (Map.Entry<String, Node> nodeEntry : nodeMap.entrySet()) {
            Node node = (Node) ((Map.Entry) nodeEntry).getValue();
            if (node.parent_id == null || node.parent_id.equals("0")) {
                treeNodeData = node;
            } else {
                nodeMap.get(node.parent_id).addChild(node);
            }
        }
        // 对多叉树进行横向排序
        treeNodeData.sortChildren();
        // 输出有序的树形菜单的JSON字符串
        return treeNodeData.toString();
    }
}