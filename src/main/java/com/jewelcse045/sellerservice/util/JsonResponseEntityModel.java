package com.jewelcse045.sellerservice.util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class JsonResponseEntityModel<T> extends JsonResponse{

	private T data;
	private int dataSize;

}
