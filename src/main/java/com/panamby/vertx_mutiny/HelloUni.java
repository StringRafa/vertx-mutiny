package com.panamby.vertx_mutiny;

import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloUni {

	public static void main(String[] args) {
		
		// Uni represents a stream that can only emit either an item or a failure event.
		Uni.createFrom().item("Hello")
			.onItem().transform(item -> item + " Mutiny!")
			.onItem().transform(String::toUpperCase)
			.subscribe().with(
					item -> log.info("Item: {}", item)
			);
		
		Uni.createFrom().item("Ignored due to failure")
			.onItem().castTo(Integer.class)
			.subscribe().with(
					item -> log.info("Item: {}", item),
					failure -> log.error("Failed with: ", failure)
			);
	}

}
