package com.migu.schedule.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 节点信息实体
 *
 * @author xufan
 * @version C10 2018年06月20日 
 * @since SDP V300R003C10
 */
public class Node
{
    private int nodeId;

    private final Map<Integer,Task> taskMap = new ConcurrentHashMap();


    public Node(int nodeId)
    {
        this.nodeId = nodeId;
    }

    /**
     * 处理未完成的任务
     *
     * @author xufan
     * @param
     * @return
    */
    public void handleUnfinishedTask(ScheduleSystem system)
    {
        taskMap.forEach((k,v)-> system.hangupQueue.offer(v));
    }

    public void removeTask(int taskId)
    {
        taskMap.remove(taskId);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Node node = (Node)o;

        return nodeId == node.nodeId;
    }

    @Override
    public int hashCode()
    {
        return nodeId;
    }
}
