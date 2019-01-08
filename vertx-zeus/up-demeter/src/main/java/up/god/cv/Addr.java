package up.god.cv;

public interface Addr {

    interface Connect {
        String ENSURE = "EVENT://CONNECT";
    }

    interface App {
        String BY_NAME = "EVENT://APP/BY/NAME";

        String ATTACH_UPLOAD = "EVENT://APP/ATTACH/UPLOAD";

        String ATTACH_DOWNLOAD = "EVENT://APP/ATTACH/DOWNLOAD";

        String INIT = "EVENT://APP/INIT";
    }
}
