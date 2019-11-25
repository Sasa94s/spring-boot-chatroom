package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import edu.udacity.java.nano.model.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String s) throws DecodeException {
        return JSON.parseObject(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null && !s.isEmpty());
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}