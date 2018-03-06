package com.project.parameter.resource;

import com.project.parameter.common.model.ERecordStatus;
import com.project.parameter.domain.dao.BankRepository;
import com.project.parameter.domain.model.Bank;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by p.ly on 3/2/2018.
 */
@Component
@Path("/banks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Bank", produces = "application/json")
public class BankResource {

  private final BankRepository bankRepository;

  @Inject
  public BankResource(BankRepository bankRepository){
    this.bankRepository = bankRepository;
  }

  @GET
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Return List of Banks")
  })
  public Response getAllBanks(){
    return Response.ok().entity(bankRepository.findAllByRecordStatus(ERecordStatus.ACTIVE)).build();
  }

  @GET
  @Path("/{id}")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Return Bank by id"),
    @ApiResponse(code = 404, message = "Given Bank not found"
    )
  })
  public Response getBankById(@PathParam("id") UUID id){
    Bank bank = bankRepository.findOne(id);

    if (bank == null)
      throw new NotFoundException();

    return Response.ok(bank).build();
  }
}