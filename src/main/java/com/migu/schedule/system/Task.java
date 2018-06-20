package com.migu.schedule.system;

/**
 * 任务实体
 *
 * @author xufan
 * @version C10 2018年06月20日 
 * @since SDP V300R003C10
 */
public class Task
{
    //  任务id
    private int taskId;

    // 资源消耗
    private int consumption;

    // 执行该任务的节点
    private int nodeId;

    public Task(int taskId)
    {
        this.taskId = taskId;
    }

    public int getTaskId()
    {
        return taskId;
    }

    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }

    public int getConsumption()
    {
        return consumption;
    }

    public void setConsumption(int consumption)
    {
        this.consumption = consumption;
    }

    public int getNodeId()
    {
        return nodeId;
    }

    public void setNodeId(int nodeId)
    {
        this.nodeId = nodeId;
    }

    public Task(int taskId, int consumption)
    {
        this.taskId = taskId;
        this.consumption = consumption;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Task task = (Task)o;

        return taskId == task.taskId;
    }

    @Override
    public int hashCode()
    {
        return taskId;
    }
}
