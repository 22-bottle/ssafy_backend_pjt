package com.ssafy.reply.model.dao;

import java.sql.SQLException;

import com.ssafy.reply.dto.ReplyDto;

public interface ReplyDao {

	void write(ReplyDto replyDto);

	void delete(String reply_no) throws SQLException;

}
