package com.ssafy.reply.model.service;

import com.ssafy.reply.dto.ReplyDto;
import com.ssafy.reply.model.dao.ReplyDao;
import com.ssafy.reply.model.dao.ReplyDaoImpl;

public class ReplyServiceImpl implements ReplyService {
	
	private static ReplyService replyService = new ReplyServiceImpl();
	private ReplyDao replyDao;
	
	private ReplyServiceImpl() {
		replyDao = ReplyDaoImpl.getReplyDao();
	}

	public static ReplyService getReplyService() {
		return replyService;
	}

	@Override
	public void write(ReplyDto replyDto) throws Exception {
		replyDao.write(replyDto);
	}

	@Override
	public void delete(String reply_no) throws Exception {
		replyDao.delete(reply_no);
	}

}
