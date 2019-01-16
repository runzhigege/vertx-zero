package up.god.micro.upload;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.up.annotations.Address;
import io.vertx.up.annotations.EndPoint;
import io.zero.epic.Ut;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@EndPoint
@Path("/api")
public class AttachmentApi {
    /**
     * category表示上传类型
     */
    @Path("/attachment/upload/{category}")
    @POST
    @Address("ZERO://FILE/UPLOAD")
    public JsonObject upload(
            @PathParam("category") final String category,
            @StreamParam final FileUpload fileUpload
    ) {
        return this.toJson(fileUpload, category);
    }

    @Path("/attachment/test")
    @GET
    public JsonObject test() {
        return new JsonObject().put("name", "Lang");
    }

    @Path("/attachment/download/{key}")
    @GET
    @Address("ZERO://FILE/DOWNLOAD")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public JsonObject download(
            @PathParam("key") final String key
    ) {
        return new JsonObject().put("key", key);
    }

    private JsonObject toJson(final FileUpload fileUpload,
                              final String category) {
        // 对应到XAttachment中的字段映射
        final JsonObject uploaded = new JsonObject();
        final String originalFile = fileUpload.fileName();
        if (Ut.notNil(originalFile)) {
            // 计算文件名
            if (originalFile.contains(".")) {
                final int lastIndex = originalFile.lastIndexOf('.');
                final String fileName = originalFile.substring(0, lastIndex);
                final String extension = originalFile.substring(lastIndex + 1);
                // 系统文件名
                final String key = UUID.randomUUID().toString();
                // file Url（下载链接）
                final String downloadUrl = "/api/attachment/download/" + key;
                uploaded.put("key", key)
                        // TODO：后期配置
                        .put("storeWay", "FILE")
                        .put("status", "PROGRESS")
                        .put("name", originalFile)
                        .put("fileKey", Ut.randomString(64))
                        .put("fileName", fileName)
                        .put("fileUrl", downloadUrl)
                        .put("filePath", fileUpload.uploadedFileName())
                        .put("extension", extension)
                        .put("module", category)
                        .put("mime", fileUpload.contentType())
                        .put("size", fileUpload.size())
                        // TODO: 后期配置
                        .put("language", "cn")
                        .put("metadata", new JsonObject().encode());
            }
        }
        return uploaded;
    }
}
