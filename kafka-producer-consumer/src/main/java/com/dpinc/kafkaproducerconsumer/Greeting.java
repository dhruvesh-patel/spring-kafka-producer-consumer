package com.dpinc.kafkaproducerconsumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Greeting {

	private String msg;
	private String name;

}
