package com.lambdaworks.redis.protocol;

import com.google.common.util.concurrent.ListenableFuture;
import com.lambdaworks.redis.RedisFuture;
import io.netty.buffer.ByteBuf;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @param <K> Key type.
 * @param <V> Value type.
 * @param <T> Output type.
 * @since 31.05.14 14:44
 */
public interface RedisCommand<K, V, T> extends ListenableFuture<T>, RedisFuture<T> {

    /**
     * The command output. Can be null.
     * 
     * @return CommandOutput<K, V, T>
     */
    CommandOutput<K, V, T> getOutput();

    /**
     * Complete a command.
     */
    void complete();

    /**
     * 
     * @return CommandArgs<K, V>
     */
    CommandArgs<K, V> getArgs();

    /**
     * Encode the command.
     * 
     * @param buf
     */
    void encode(ByteBuf buf);

    /**
     * Subclasses should invoke this method to set the result of the computation to an error, {@code throwable}. This will set
     * the state of the future to COMPLETED and invoke the listeners if the state was successfully changed.
     * 
     * @param throwable the exception that the task failed with.
     * @return true if the state was successfully changed.
     */
    boolean setException(Throwable throwable);
}