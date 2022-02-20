package com.adrianmensing.lingo.game.action;

import com.adrianmensing.lingo.game.response.GameResponse;

public interface ActionHandler {
    GameResponse handle(Action<?> action);
}
