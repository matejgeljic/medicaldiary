package ba.academy.diary;

import ba.academy.diary.dto.DiaryInput;
import ba.academy.diary.services.DiaryService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/diary")
public class DiaryResource {

  @Inject DiaryService diaryService;
  // GET - get by id
  // DELETE - by id
  // PUT - update by id

  @GET
  public Response getDiary()
  {
    List<DiaryInput> allDiaryInputs = diaryService.getDiary();
    if(allDiaryInputs == null || allDiaryInputs.isEmpty()) {
      return Response.noContent().build();
    }
    return  Response.ok(allDiaryInputs).build();
  }

  @GET
  @Path("/{id}")
  public Response getSingleDiary(@PathParam("id") Integer id) {
    DiaryInput singleDiary = diaryService.getSingleDiary(id);
    if(singleDiary == null || id == null) {
      return Response.noContent().build();
    }
    return Response.ok(singleDiary).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteDiary(@PathParam("id") Integer id) {
    List<DiaryInput> newDiaryInputs = diaryService.deleteDiary(id);
    if(newDiaryInputs == null || id == null) {
      return Response.noContent().build();
    }
    return Response.ok(newDiaryInputs).build();
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)

  public Response createDiary(DiaryInput diaryInput, @Context UriInfo uriInfo)
  {
    DiaryInput storedDiary = diaryService.addDiaryInput(diaryInput);
    if(storedDiary != null) {
      UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
      uriBuilder.path(Integer.toString(storedDiary.getId()));
      return Response.created(uriBuilder.build()).entity(storedDiary).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces(value = MediaType.APPLICATION_JSON)

  public Response editDiary(@PathParam("id") Integer id, DiaryInput diaryInput, @Context UriInfo uriInfo) {
    DiaryInput updatedDiary = diaryService.editDiary(id, diaryInput);
    if(updatedDiary != null) {
      UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
      uriBuilder.path(Integer.toString(updatedDiary.getId()));
      return Response.created(uriBuilder.build()).entity(updatedDiary).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).build();
  }
}
