package com.ssafy.reply.model.service;

import com.ssafy.reply.dto.ReplyDto;

public interface ReplyService {

	void write(ReplyDto replyDto) throws Exception;

}
