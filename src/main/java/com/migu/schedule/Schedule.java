package com.migu.schedule;

import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;
import com.migu.schedule.system.Node;
import com.migu.schedule.system.ScheduleSystem;
import com.migu.schedule.system.Task;

import java.util.List;

/*
*类名和方法不能修改
 */
public class Schedule
{

    ScheduleSystem system;

    public int init()
    {
        // 初始化系统
        if (system == null)
        {
            system = new ScheduleSystem();
        }
        else
        {
            // 如果实例已有,则清空内部所有信息
            system.clear();
        }
        return ReturnCodeKeys.E001;
    }

    public int registerNode(int nodeId)
    {
        // 节点非法
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }
        Node o = system.nodeMap.putIfAbsent(nodeId, new Node(nodeId));
        // 已有节点
        if (o != null)
        {
            return ReturnCodeKeys.E005;
        }
        // 节点注册成功
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId)
    {
        // 节点非法
        if (nodeId <= 0)
        {
            return ReturnCodeKeys.E004;
        }

        Node node = system.nodeMap.remove(nodeId);
        // 节点未注册
        if (node == null)
        {
            return ReturnCodeKeys.E007;
        }
        // 处理运行中的任务,然后返回注销成功
        node.handleUnfinishedTask(system);
        return ReturnCodeKeys.E006;
    }

    public int addTask(int taskId, int consumption)
    {
        // 任务编号非法
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }

        Task n = new Task(taskId, consumption);

        Task o = system.taskMap.putIfAbsent(taskId, n);
        // 已有任务
        if (o != null)
        {
            return ReturnCodeKeys.E010;
        }
        // 新任务入栈, 任务添加成功
        system.hangupQueue.offer(n);

        return ReturnCodeKeys.E008;
    }

    public int deleteTask(int taskId)
    {
        // 任务编号非法
        if (taskId <= 0)
        {
            return ReturnCodeKeys.E009;
        }
        Task task = system.taskMap.remove(taskId);
        if (task == null)
        {
            return ReturnCodeKeys.E012;
        }

        int nodeId = task.getNodeId();
        // 如果未分配,从挂起队列中删除
        if (nodeId <= 0)
        {
            system.hangupQueue.remove(new Task(taskId));
        }
        // 如果已分配,从节点中删除
        else
        {
            system.nodeMap.get(nodeId).removeTask(taskId);
        }
        return ReturnCodeKeys.E011;
    }

    public int scheduleTask(int threshold)
    {
        if (threshold <= 0)
        {
            return ReturnCodeKeys.E002;
        }
        if (system.scheduleTask(threshold))
        {
            // TODO 方法未实现
            return ReturnCodeKeys.E013;
        }
        else
        {
            return ReturnCodeKeys.E014;
        }
    }

    public int queryTaskStatus(List<TaskInfo> tasks)
    {
        // TODO 方法未实现
        return ReturnCodeKeys.E000;
    }

}
