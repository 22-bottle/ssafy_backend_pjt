<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp" %>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">에러발생!!!</mark>
          </h2>
        </div>
        
       <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <div class="fw-bold text-danger pt-3" >처리 중 에러 발생 T.T</div>
              <div class="card-body">
              <p class="card-text" style="font-size:15px">
              <br>
                ${msg}
              <br>
              <br>
              </p>
                	
              </div>
              <button type="button" id="btn-index" class="btn btn-outline-danger">
                메인 페이지 이동...
              </button>
              </h2>
            </div>
          </div>
        </div>
 	
 	


    <script>
      document.querySelector("#btn-index").addEventListener("click", function () {
        location.href = "${root}";
      });
    </script>
<%@ include file="/common/footer.jsp" %>