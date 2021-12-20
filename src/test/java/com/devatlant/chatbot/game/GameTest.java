package com.devatlant.chatbot.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.api.objects.Message;
import java.io.IOException;
import java.util.Random;

import static java.lang.String.*;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;


class GameTest {
    private Game testSubject;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        testSubject = new Game(100, new Random());
    }

    @org.junit.jupiter.api.Test
    void should_respond_with_start() {
        Message start = buildMessage("/start");
        ResponseWithCounter res = testSubject.reactOnGamerMessage(start);
        assertEquals(RESPONSE.START, res.code);
    }

    public Message buildMessage(final String text){
        try {
            return objectMapper.readValue(format("{\"text\":\"%s\"}", text), Message.class);
        } catch (IOException e) {
           throw new RuntimeException("wrong json syntax for "+ text,e);
        }
    }

    @Test
    public void should_return_answer_with_counter(){
        //given
        testSubject = new Game(100, new Random());
        Message message = buildMessage("1");
        //run
        ResponseWithCounter res = testSubject.reactOnGamerMessage(message);

        // assert
        assertEquals(1, res.counter);
    }
    @Test
    public boolean  should_return_false_for_text_anyText(){
        boolean test = Game.isInteger("text");
        if (test) return false;
        else return true;
    }

    @Test
    public boolean should_return_true_for_int_100(){
       boolean test = Game.isInteger("100");
       if (test) return true ;
       else  return false;

    }

}
