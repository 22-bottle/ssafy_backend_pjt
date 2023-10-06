<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
	<c:if test="${article eq null}">
		<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/article?action=list";
		</script>
	</c:if>
      <%@ include file="/common/confirm.jsp" %>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글보기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">${article.articleNo}. ${article.subject}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${article.userId}</span> <br />
                  <span class="text-secondary fw-light"> ${article.registerTime} 조회 : ${article.hit} </span>
                </p>
              </div>
            </div>
            <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${article.content}
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">글목록</button>
              <c:if test="${userInfo.userId eq article.userId}">
              	<button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">글수정</button>
              	<button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
              </c:if>
            </div>
            
            <div>
            	<c:forEach items="${ article.replies }" var="reply">
            		<span>${ reply.content }</span>
            		<button class="delete_btn" value="${ reply.reply_no }">삭제</button></br>
            	</c:forEach>
            </div>
            
            <div>
            	<div class="col-lg-8 col-md-10 col-sm-12">
          			<form id="reply-form" method="POST" action="">
          				<input type="hidden" name="action" value="write">
          				<input type="hidden" name="article_no" value="${article.articleNo}">
            			<div class="mb-3">
              				<label for="content" class="form-label">댓글 : </label>
              				<textarea class="form-control" id="reply" name="content" rows="7"></textarea>
            			</div>
            			<div class="col-auto text-center">
              				<button type="button" id="btn-reply" class="btn btn-outline-primary mb-3">댓글 작성</button>
            			</div>
          			</form>
        		</div>
            </div>
            
          </div>
        </div>
      </div>
    </div>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/article?action=list";
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        alert("글수정하자!!!");
        location.href = "${root}/article?action=mvmodify&articleno=${article.articleNo}";
      });
      document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글삭제하자!!!");
        location.href = "${root}/article?action=delete&articleno=${article.articleNo}";
      });
    </script>
    
    <script>
      document.querySelector("#btn-reply").addEventListener("click", function () {
        if (!document.querySelector("#reply").value) {
          alert("댓글 입력!!");
          return;
        } else {
          let form = document.querySelector("#reply-form");
          form.setAttribute("action", "${root}/reply");
          form.submit();
        }
      });
    </script>
    
    <script>
    	let delete_btns = document.querySelectorAll(".delete_btn")
    	for (let i = 0; i < delete_btns.length; i++) {
    		delete_btns[i].addEventListener("click", function() {
    			fetch("${root}/reply?action=delete&article_no=${article.articleNo}&reply_no=" + delete_btns[i].value)
    				.then((request) => location.reload());
    		})
    	}
    </script>
<%@ include file="/common/footer.jsp" %>