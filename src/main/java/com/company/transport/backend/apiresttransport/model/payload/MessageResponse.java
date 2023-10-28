package com.company.transport.backend.apiresttransport.model.payload;

import java.util.List;

import com.company.transport.backend.apiresttransport.model.entity.Vehicle;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MessageResponse <T>{
	private String message;
	private T object;
}
