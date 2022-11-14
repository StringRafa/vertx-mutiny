package com.panamby.vertx_mutiny;

import java.util.stream.IntStream;

import io.smallrye.mutiny.Multi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloMulti {

	public static void main(String[] args) {
		// Multi represents a stream of data. A stream can emit 0, 1, n, or an infinite number of items.
		Multi.createFrom().items(IntStream.rangeClosed(0, 10).boxed())
//			.onItem().transform(value -> value * 2)
			.onItem().transform(value -> value / 0)
			.onFailure().invoke(failure -> log.error("Transformation failed with: ", failure))
			.onItem().transform(String::valueOf)
//			.onFailure().recoverWithItem("fallback")
//			.select().last(2)
			.select().first(4)
			.subscribe().with(log::info, 
					failure -> log.error("Failed with: ", failure)
			);
	}

}
