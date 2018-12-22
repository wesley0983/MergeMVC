<%@page import="com.product.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	ProductVO proVO = (ProductVO) request.getAttribute("proVO"); //EmpServlet.java (Concroller) �s�Jreq��proVO���� (�]�A�������X��proVO, �]�]�A��J��ƿ��~�ɪ�proVO����)
    
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
		.ee{
            background-color: #0f5;
		}
		.ff{
			background-color: #E4D10F;
		}
		.dropdown-menu li:hover .sub-menu { /*�I��navbar�|�U��*/
			visibility: visible;
		}
		.dropdown:hover .dropdown-menu { /*�I��navbar�|�U��*/
			display: block;
		}

		.backgc { /*�I������*/
			background-color: #F6F6F6;
		}

		.navsize{ /*navbar�e�פj�p*/
            width: 1200px;
		}
        
		.warp { /*�ӫ~����*/
			box-shadow: 0 0.2rem 0.4rem rgba(0, 0, 0, .09);
			background-color: rgb(252, 252, 253);
			padding: 20px 48px;
			width: 1200px;
			margin: auto;
            
		}
		
		.warpwidth{ /*�ӫ~�����j�p*/
			width: 1104px;
		}
		.fontsize{ /*��r����*/
		    font-size: 18px;
		    font-weight: 400;
		    color: #000;
		    margin: 30px 0 15px;
		    text-align: left;
		}
		.fontsize_s{ /*��r���e����*/
			min-height: 40px;
    		color: #666;
		}
        .valuesize{ /*value����*/
        	min-height: 40px;
        }
        .buttonsize{ /*���s�j�p*/
        	text-align: center;
        }
        .tablebgc{
        	background-color: #F7F5F5;
        }
        .thwidth{ /*�ӫ~�W�ٹϤ��j�p*/
        	width: 250px;
        }
        table{
		    border-collapse:separate;
		    border-spacing:0 0.5rem;
        }

        th {
        	text-align: center;
        }
        td {
        	text-align: center;
        }
        #pre01 {
			width: 300px;
			height: auto;
		}
        .imgsize {
			width: 300px;
			height: auto;
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


				<!-- ��� -->
					<FORM METHOD="post" ACTION="pro.do" name="form1" enctype="multipart/form-data">
						<div class="container-fluid warp">
			                <div class="row">
							<!-- �e���� -->
							    <div class="container-fluid warpwidth">
							    	<div class="row">
							    		<div>
							    			<h2 class="fontsize">�s��ӫ~�Ӥ�</h2>
							    		</div>
							    		<!-- �ɮפW�� -->
								    		<div>
								    			<input type="file" name="pro_pic" id="file01">
								    		</div>
							    		<!-- �Ϥ��w���s�� -->
								    		<div id="preset">
								    			<img class="imgsize" src="<%=request.getContextPath()%>/pro/proImg.do?pro_no=<%= proVO.getPro_no()%>">
								    		</div>
			                            <!-- �Ϥ��w�� -->
								    		<div id="display">
								    			<img id="pre01" class="imgsize">
								    		</div>
							    		    <!-- ��r���1 -->
								    			<div class="container-fluid ">
									    			<div class="row">
									    				<div class="fontsize">
									    					�򥻸�T :
									    				</div>
									    				<!-- ���ť� -->
										    				<div class="col-xs-12 col-sm-1 ">
										    				    <div class="row">
											    				    	��
										    				    </div>
										    				</div>
									    				<!-- ���e�Ա� -->
										    				<div class="col-xs-12 col-sm-10 ">
				                                                <div class="col-xs-12 col-sm-2 ">
											    					<div class="fontsize_s">
											    						�ӫ~���O : �ο諸				    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~�W�� :				    						
											    					</div>
											    					<div class="fontsize_s">
											    						�Ӥ����ɦW :			    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~�W�� :				    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~�ԭz :				    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~���A :				    						
											    					</div>

				                                                </div>
				                                                <div class="col-xs-12 col-sm-8 ">
				                                                	<!-- �ӫ~���O -->
				                                                	    <jsp:useBean id="productClassSvc" scope="page" class="com.productclass.model.ProductClassService" />
					                                                    <div class="valuesize">
					                                                    	<select size="1" name="pro_classid">
																				<c:forEach var="productClassVO" items="${productClassSvc.all}">
																					<option value="${productClassVO.pro_classid}" ${(productClassVO.pro_classid==productClassVO.pro_classid)?'selected':'' } >${productClassVO.pro_classname}
																				</c:forEach>
																			</select>
					                                                    </div>
				                                                	<!-- �ӫ~�W�� -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="ename" size="45" value="<%= proVO.getPro_name()%>" />
					                                                	</div>
				                                                	<!-- �Ӥ����ɦW -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="pic_ext" size="45" value="<%= proVO.getPro_pic_ext()%>" />
					                                                	</div>
				                                                	<!-- �ӫ~�W�� -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="format" size="45" value="<%=  proVO.getPro_format()%>" />
					                                                	</div>
				                                                	<!-- �ӫ~�ԭz -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="pro_details" size="45" value="<%=  proVO.getPro_details()%>" />
					                                                	</div>
				                                                	<!-- �ӫ~���A -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="pro_shelve" size="45" value="<%=  proVO.getPro_shelve()%>" />
					                                                	</div>
				                                                </div>
				                                                <div class="col-xs-12 col-sm-2 ">
				                                                </div>
										    				</div>
									    				<!-- �k�ť� -->
										    				<div class="col-xs-12 col-sm-1 ">
										    					�k
										    				</div>
									    			</div>
								    			</div>
							    			<!-- ��r���2 -->
								    			<div class="container-fluid ">
									    			<div class="row">
									    				<div class="fontsize">
									    					�ӫ~����ήw�s :
									    				</div>
									    				<!-- ���ť� -->
										    				<div class="col-xs-12 col-sm-1 ">
										    				    <div class="row">
											    				    	��
										    				    </div>
										    				</div>
									    				<!-- ���e�Ա� -->
										    				<div class="col-xs-12 col-sm-10 ">
				                                                <div class="col-xs-12 col-sm-2 ">
											    					<div class="fontsize_s">
											    						�ӫ~��� : 				    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~�w�s�q :				    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~�w���w�s�q :			    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~�`���� :				    						
											    					</div>
											    					<div class="fontsize_s">
											    						�ӫ~�����`�H�� :				    						
											    					</div>
				                                                </div>
				                                                <div class="col-xs-12 col-sm-8 ">
				                                                	<!-- �ӫ~��� -->
					                                                    <div class="valuesize">
					                                                    	<input type="TEXT" name="pro_bonus" size="45" value="<%= proVO.getPro_bonus()%>" />
					                                                    </div>
				                                                	<!-- �ӫ~�w�s�q -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="pro_stock" size="45" value="<%= proVO.getPro_stock()%>" />
					                                                	</div>
				                                                	<!-- �ӫ~�w���w�s�q -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="pro_safestock" size="45" value="<%= proVO.getPro_safestock()%>" />
					                                                	</div>
				                                                	<!-- �ӫ~�`���� -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="pro_all_assess" size="45" value="<%= proVO.getPro_all_assess()%>" />
					                                                	</div>
				                                                	<!-- �ӫ~�����`�H�� -->
					                                                	<div class="valuesize">
					                                                		<input type="TEXT" name="pro_all_assessman" size="45" value="<%= proVO.getPro_all_assessman()%>" />
					                                                	</div>
				                                                </div>
				                                                <div class="col-xs-12 col-sm-2 ">
				                                                </div>
										    				</div>
									    				<!-- �k�ť� -->
										    				<div class="col-xs-12 col-sm-1 ">
										    					�k
										    				</div>
									    			</div>
								    			</div>
		                                    
							    			<!-- �e�X���s -->
									    		<div style="text-align: center;">
									    			<input type="hidden" name="action" value="update">
													<input type="hidden" name="pro_no" value="<%=proVO.getPro_no()%>">
													<input type="submit" value="�e�X�ק�">
									    		</div>
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
			document.getElementById("display").style.display = 'none';
		    $(function() {  //�N�Ϥ��w��
		    	$('input[type=file]').change(function() {
		      	var input = $(this);
		      	document.getElementById("preset").style.display = 'none';
		      	document.getElementById("display").style.display = 'block';
		      	if(!!this.files && !!this.files[0]) {
		        	var reader = new FileReader();
		          reader.onload = function(e) {
		          	$('#pre' + input.prop('id').substr(4,2)).prop('src', e.target.result);
		          }
		          reader.readAsDataURL(this.files[0]);
		        }
		      });
		    });	    
		</script>
	</body>
</html>