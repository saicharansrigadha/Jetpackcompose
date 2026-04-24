package ViewModels;

import localdb.entity.TaskLatLongbyUserIdEntity;
import Repository.TaskRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import communication.TaskInfoServiceClient;
import fragments.resetpassword_fragment_dialog;
import fragments.taskupdate_fragment_dialog;
import localdb.entity.*;
import org.json.JSONException;
import org.json.JSONObject;
import util.AppSharedPreferences;
import util.ObservableTaskRunningStatus;
import util.myapplication;

import java.util.List;

public class TaskViewModel extends ViewModel {
    public static TaskEntity selectedTask = null;
    public static TimelineEntity selectedTimelineItem = null;
    public static String SelectedTaskLink = "";
    public static String SelectedTaskSubLink = "";
    public static String SpanType = "";

    TaskRepository tasksRepo = TaskRepository.getInstance();

    public LiveData<List<TaskEntity>> getTasks(int userid) {
        return tasksRepo.getTasks(userid);
    }

    public LiveData<List<TaskEntity>> getTasksByLinkCode(String linkCode,String spantype) {
        return tasksRepo.getTasksByLinkCode(linkCode,spantype);
    }
    public LiveData<List<TaskEntity>> getTasksBySublinkName(String sublinkName,String linkName) {
        return tasksRepo.getTasksBySyblinkLinkName(sublinkName,linkName);
    }

    public List<TaskPendingApprovalEntity> getTaskPendingApprovalList(int userId) {
        return tasksRepo.getPendingApprovalTasks(userId);
    }
    public LiveData<List<TaskPendingApprovalEntity>> getAllLivePendingApprovalTaskById(int userId) {
        return tasksRepo.getAllLivePendingApprovalTaskById(userId);
    }
    public List<TaskLatLongbyUserIdEntity> getLatLongbyUserId() {
        return tasksRepo.getLatLongbyUserId();
    }
    public List<BarCodeValueEntity> getPoleCodeValue() {
        return tasksRepo.getPoleCodeValue();
    }
    public List<TaskCountEntity> getTasKCountList(String task_id) {
        return tasksRepo.getTasKCountList( task_id);
    }
    public void updateIconStatusToYesById(int id) {
         tasksRepo.updateIconStatusToYesById(id);
    }
    public List<LatLongByPoleCodeEntity> getLatLongbyUserIdPoleCode() {
        return tasksRepo.getLatLongbyUserIdPoleCode();
    }
    public List<TaskCountEntity> getDashboardTaskCount() {
        return tasksRepo.getDashboardTaskCount();
    }
 public List<LatLongByPoleCodeEntity> getAllLatLongNosbyUserId() {
        return tasksRepo.getAllLatLongNosbyUserId();
    }

    public void inertLatLongbyUserId(TaskLatLongbyUserIdEntity taskLatLongbyUserIdEntity) {
        tasksRepo.insertLatLongData(taskLatLongbyUserIdEntity);
    }
    public List<TaskEntity> getTasksData(int userid) {
        return tasksRepo.getTasksData(userid);
    }
 /*   public List<String> getAllSpamType() {
        return tasksRepo.getTasksSpamType();
    }*/
    public List<TaskEntity> getTasksDataBySublinkCode(String sublinkName,String linkName) {
        return tasksRepo.getTasksDataByLinkCode(sublinkName,linkName);
    }
    public TaskEntity getTaskById(int taskid) {
        return tasksRepo.getTaskById(taskid);
    }

    public TaskPendingApprovalEntity getPendingApprovalTaskById(int taskid) {
        return tasksRepo.getPendingTaskById(taskid);
    }
    public TimelineEntity getRejectedTaskProgress(int timelineid) {
        return tasksRepo.getRejectedTimeLineById(timelineid);
    }

    public void deleteTasks(int userid) {
        tasksRepo.deleteAllTasks(userid);
    }

    public void deletePendingApprovalTasks(int userid) {
        tasksRepo.deleteAllPendingApprovalTasks(userid);
    }

    public void syncTasksData(int userid) {
         TaskInfoServiceClient.syncTasksData(userid, tasksRepo);
    }

    public void synPendingApprovalTasksData(int userid) {
        TaskInfoServiceClient.syncUserProgressRejectedList(userid, tasksRepo);
    }

    public void updateTaskProgress(JSONObject taskProgressModel, taskupdate_fragment_dialog taskupdate_fragment_dialog) {
        TaskInfoServiceClient.UpdateTaskProgress(taskProgressModel, taskupdate_fragment_dialog);
    }

    public List<String> getAllLinksName(int userid,String spantype) {
        return tasksRepo.getAllLinksName(userid,spantype);
    }
    public List<String> getAllSubLinksName(String linkCode) {
        return tasksRepo.getAllSubLinksName(linkCode);
    }

    public void insertTaskCheckin(TaskCheckinModelEntity taskCheckinModelEntity, ObservableTaskRunningStatus observableTaskRunningStatus) {
        JSONObject jsonBody = new JSONObject();
        try {

            tasksRepo.insertTaskCheckin(taskCheckinModelEntity);

            jsonBody.put("Id", taskCheckinModelEntity.getId());
            jsonBody.put("TaskId", taskCheckinModelEntity.getTaskId());
            jsonBody.put("RouteId", taskCheckinModelEntity.getRouteId());
            jsonBody.put("UserId", AppSharedPreferences.getUserid(myapplication.getInstance()));
            jsonBody.put("CheckinDate", taskCheckinModelEntity.getCheckinDate());
            jsonBody.put("CheckOutDate", taskCheckinModelEntity.getCheckOutDate());
            jsonBody.put("FolderPath", taskCheckinModelEntity.getFolderPath());
            jsonBody.put("Date", taskCheckinModelEntity.getDate());
            jsonBody.put("IsActive", true);

            TaskInfoServiceClient.doCheckin(tasksRepo, jsonBody, observableTaskRunningStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void insertTaskCheckOut(TaskCheckinModelEntity taskCheckinModelEntity, ObservableTaskRunningStatus observableTaskRunningStatus) {
        JSONObject jsonBody = new JSONObject();
        try {

            tasksRepo.insertTaskCheckin(taskCheckinModelEntity);

            jsonBody.put("Id", taskCheckinModelEntity.getId());
            jsonBody.put("TaskId", taskCheckinModelEntity.getTaskId());
            jsonBody.put("RouteId", taskCheckinModelEntity.getRouteId());
            jsonBody.put("UserId", AppSharedPreferences.getUserid(myapplication.getInstance()));
            jsonBody.put("CheckinDate", taskCheckinModelEntity.getCheckinDate());
            jsonBody.put("CheckOutDate", taskCheckinModelEntity.getCheckOutDate());
            jsonBody.put("FolderPath", taskCheckinModelEntity.getFolderPath());
            jsonBody.put("Date", taskCheckinModelEntity.getDate());
            jsonBody.put("IsActive", true);

            TaskInfoServiceClient.doCheckout(tasksRepo, jsonBody, observableTaskRunningStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void syncTaskTimlineData(int taskid) {
        TaskInfoServiceClient.syncTasksTimeline(taskid, tasksRepo);
    }

    public List<TimelineEntity> getTasksTimline(int taskid) {
        return tasksRepo.getTaskTimeline(taskid);
    }


    public void resetPassword(int userid, String oldPassword, String newPassword, resetpassword_fragment_dialog resetpassword_fragment_dialog) {
        TaskInfoServiceClient.resetPassword(userid, oldPassword, newPassword, resetpassword_fragment_dialog);
    }



    public void SyncLookupsData(int userid){

    }
    // Expose LiveData for MainActivity
    private MutableLiveData<List<String>> spamTypesLiveData = new MutableLiveData<>();

    public LiveData<List<String>> getAllSpamType() {
        // Fetch from repo and post value
        new Thread(() -> {
            List<String> list = tasksRepo.getAllSpamType();
            spamTypesLiveData.postValue(list);
        }).start();
        return spamTypesLiveData;
    }
}
