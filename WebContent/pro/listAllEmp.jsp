<%@page import="com.product.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	ProductService proSvc = new ProductService();
    List<ProductVO> list = proSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">

	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>Title Page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<!--[if lt IE 9]>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.aa {
			background-color: #faa;
		}
		.bb {
			background-color: #afa;
		}
		.cc {
			background-color: #aaf;
		}
		.dd {
		    background-color: #aaa;
		}

		.ee {
			background-color: #0f5;
		}

		.ff {
			background-color: #E4D10F;
		}

		.dropdown-menu li:hover .sub-menu {
			/*�I��navbar�|�U��*/
			visibility: visible;
		}

		.dropdown:hover .dropdown-menu {
			/*�I��navbar�|�U��*/
			display: block;
		}

		.backgc {
			/*�I������*/
			background-color: #F6F6F6;
		}

		.navsize {
			/*navbar�e�פj�p*/
			width: 1200px;
		}

		.warp {
			/*�ӫ~����*/
			box-shadow: 0 0.2rem 0.4rem rgba(0, 0, 0, .09);
			background-color: rgb(252, 252, 253);
			padding: 20px 48px;
			width: 1200px;
			margin: auto;

		}

		.warpwidth {
			/*�ӫ~�����j�p*/
			width: 1104px;
		}

		.fontsize {
			/*��r����*/
			font-size: 18px;
			font-weight: 400;
			color: #000;
			margin: 30px 0 15px;
			text-align: left;
		}

		.fontsize_s {
			/*��r���e����*/
			min-height: 40px;
			color: #666;
		}

		.valuesize {
			/*value����*/
			min-height: 40px;
		}

		.buttonsize {
			/*���s�j�p*/
			text-align: center;
		}

		.tablebgc {
			background-color: #F7F5F5;
		}

		.thwidth {
			/*�ӫ~�W�ٹϤ��j�p*/
			width: 250px;
		}

		table {
			border-collapse: separate;
			border-spacing: 0 0.5rem;
		}

		th {
			text-align: center;
		}

		td {
			text-align: center;
		}
		.imgsize {
			width: 80px;
			height: auto;
		}
		.pagecenter{
            text-align: center;
		}
		</style>
	</head>

	<body>

		<div class="container-fluid backgc">
			<div class="row">

				<!-- navbar -->
				<div class="container-fluid ">
					<div class="row">
						<nav class="navbar navbar-default" role="navigation">
							<div class="container">
								<div class="navbar-header">
									<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
										<span class="sr-only">������</span>
										<span class="icon-bar"></span>
										<span class="icon-bar"></span>
										<span class="icon-bar"></span>
									</button>
									<a class="navbar-brand" href="#">SPORTGO</a>
								</div>

								<!-- ������ÿ��� -->
								<div class="collapse navbar-collapse navbar-ex1-collapse ">
									<!-- ����� -->
									<ul class="nav navbar-nav">
										<li class="active">
											<a href="#">�ڪ��ӫ~</a>
										</li>
										<li>
											<a href="#">�ڪ��������</a>
										</li>
										<li>
											<a href="#">�ڪ��P��</a>
										</li>
										<li>
											<a href="#">�ڪ���P����</a>
										</li>
										<li>
											<a href="#">�ڪ��i�b</a>
										</li>
										<li>
											<a href="#">�ڪ����]</a>
										</li>
										<li>
											<a href="#">����]�w</a>
										</li>
									</ul>
									<!-- �k��� -->
									<ul class="nav navbar-nav navbar-right">

										<li>
											<a href="#"></a>
										</li>
										<li>
											<a href="#">�ӤH�]�w</a>
										</li>
										<li class="dropdown">
											<a href="#" class="dropdown-toggle" data-toggle="dropdown">�c�餤��
												<b class="caret"></b>
											</a>
											<ul class="dropdown-menu">
												<li>
													<a href="#">�������R�a</a>
												</li>
												<li>
													<a href="#">�n�X</a>
												</li>
											</ul>
										</li>
									</ul>
								</div>
								<!-- ������ÿ��ϵ��� -->
							</div>
						</nav>
					</div>
				</div>

				<%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">�Эץ��H�U���~:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<!-- ��� -->
					<FORM METHOD="post" ACTION="pro.do" name="form1" enctype="multipart/form-data">
						<div class="container-fluid warp">
							<div class="row">
								<!-- �e���� -->
								<div class="container-fluid warpwidth">
									<div class="row">
										<div>
											<h2 class="fontsize">?��ӫ~</h2>
										</div>
										<!-- ����r�j�M -->
										<div class="container">
											<div class="row">
												<div class="col-xs-12 col-sm-4">
													<div class="row">
														<!-- �j�M��� -->
														<div class="input-group">
															<input type="text" class="form-control" placeholder="�п�J����r">
															<span class="input-group-btn">
																<button class="btn btn-info" type="button">�j�M</button>
															</span>
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-4"></div>
												<div class="col-xs-12 col-sm-4"></div>
											</div>
										</div>

										<!-- �Ϥ��w�� -->
										<div><%@ include file="page1.file" %></div>
										<table class="table table-hover ">
											<thead>
												<tr class="tablebgc">
													<th class="thwidth">�ӫ~�W�ٹϤ�</th>
													<th>�ӫ~�s��</th>
													<th>�ӫ~���O</th>
													<th>�ӫ~���</th>
													<th>�ӫ~�w�s</th>
													<th>�ӫ~���A</th>
													<th>�ާ@</th>
												</tr>
											</thead>
											<tbody>
												
													<jsp:useBean id="productClassSvc" scope="page" class="com.productclass.model.ProductClassService" />
													<c:forEach var="proVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
														<tr>
															<!-- �ӫ~�Ϥ��W�� -->
															<td style="text-align: left;">
																<img class="imgsize" src="<%=request.getContextPath()%>/pro/proImg.do?pro_no=${proVO.pro_no}"> ${proVO.pro_name}
															</td>
															<!-- �ӫ~�s�� -->
															<td>${proVO.pro_no}</td>
															<!-- �ӫ~���O -->
															<td>
																<c:forEach var="productClassVO" items="${productClassSvc.all}">
																	${(proVO.pro_classid == productClassVO.pro_classid)?productClassVO.pro_classname:''}
																</c:forEach>
															</td>
															<!-- �ӫ~��� -->
															<td>
																${proVO.pro_bonus}
															</td>
															<!-- �ӫ~�w�s -->
															<td>
																${proVO.pro_stock}
															</td>
															<!-- �ӫ~���A -->
															<td>
																${proVO.pro_shelve}
															</td>
															<!-- �U�Ԧ����s -->
															<td>
																<div class="btn-group">
																	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
																		<i class="fa fa-pencil-square">
																			�s��
																		</i>
																		<span class="caret"></span>
																	</button>
																	<ul class="dropdown-menu" role="menu">
																		<li>
																			<a href="#"></a>
																		</li>
																		<li>
																			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/pro.do" style="margin-bottom: 0px;">
																				<input type="submit" value="�ק�">
																				<input type="hidden" name="pro_no" value="${proVO.pro_no}">
																				<input type="hidden" name="action" value="getOne_For_Update">
																			</FORM>
																		</li>
																		<li>
																			<a href="#">�W�[</a>
																		</li>
																		<li>
																			<a href="#">�U�[</a>
																		</li>
																		<li class="divider"></li>
																		<li>
																			<a href="#">���]�m</a>
																		</li>
																	</ul>
																</div>
															</td>
														</tr>
													</c:forEach>
													
											</tbody>
										</table>
										<%@ include file="page2.file" %>
									</div>
								</div>
							</div>
						</div>
					</FORM>


			</div>
		</div>


			<script src="https://code.jquery.com/jquery.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
			<script type="text/javascript">
				// document.getElementById("display").style.display = 'none';
				//    $(function() {  //�N�Ϥ��w��
				//    	$('input[type=file]').change(function() {
				//      	var input = $(this);
				//      	document.getElementById("preset").style.display = 'none';
				//      	document.getElementById("display").style.display = 'block';
				//      	if(!!this.files && !!this.files[0]) {
				//        	var reader = new FileReader();
				//          reader.onload = function(e) {
				//          	$('#pre' + input.prop('id').substr(4,2)).prop('src', e.target.result);
				//          }
				//          reader.readAsDataURL(this.files[0]);
				//        }
				//      });
				//    });
			</script>							
	</body>						
</html>						