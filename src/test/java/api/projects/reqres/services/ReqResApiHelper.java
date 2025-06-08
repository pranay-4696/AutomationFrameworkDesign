package api.projects.reqres.services;

public class ReqResApiHelper {

    private static ReqResApiController reqResApiController;

    public static ReqResApiController getReqResApiController() {
        if (reqResApiController == null) {
            reqResApiController = new ReqResApiController();
        }
        return reqResApiController;
    }
}