<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:app>
    <jsp:body>		
	    <div class="container p4p">
	        <div class="rowContainer">	    
		        <h2 class="textAlignCenter">Welcome to Graph Partitioning System<br><br></h2>    
		    	<div class="row itemsSpaceAround">				
		    		<div class="col-md-6">
						<form method="POST" id="fileForm" enctype="multipart/form-data" action="/GraphPartitioning/upload.html" modelAttribute="fileInput">
									<legend>Upload .txt file with graph data*</legend>
									<input type="file" name="fileInput" id="fileInput" accept=".txt"/>
								<br/>
								<fieldset class="form-group" id="dataFormats">
								    <legend>Choose data format*</legend>
									    <div class="form-check">
									        <label class="form-check-label">
										    <input type="radio" class="form-check-input" name="dataFormat" id="matrix" value="matrix">
										        Graph adjacency matrix
											    </label>
										 </div>
										 <div class="form-check">
										    <label class="form-check-label">
										    <input type="radio" class="form-check-input" name="dataFormat" id="row" value="row">
										       Graph List
										    </label>
						    			 </div>
							 	</fieldset>
							 	<fieldset class="form-group" id="separators">
								    <legend>Choose separator</legend>
									    <div class="form-check">
									        <label class="form-check-label">
										    <input type="radio" class="form-check-input" name="separator" id="comma" value="comma">
										        Comma separator
											    </label>
										 </div>
										 <div class="form-check">
										    <label class="form-check-label">
										    <input type="radio" class="form-check-input" name="separator" id="space" value="space">
										       Space separator
										    </label>
						    			 </div>
							 	</fieldset>
								<button type="submit" id="submit" class="btn btn-primary"> Upload </button>
						</form>
					</div>
					<div class="col-md-6 textAlignCenter">
						<h3>Examples data format</h3><br/>		
						<div>
							<div class="pr25">
								<h4>1. Graph Adjacency matrix</h4>
								<div class="row itemsCentered itemsSpaceAround">
									<div class="col-md-6 pr10">
										<h4>Space separator</h4>				
											0 1 0  <br>
											1 0 1  <br>
											0 1 0  <br>
									</div>
									<div class="col-md-6">
										<h4>Comma separator</h4>	
											0, 1, 0,  <br>
											1, 0, 1,  <br>
											0, 1, 0,  <br>
									</div>
								</div>
							</div>
							<div>
								<h4>2. Graph List</h4>
								<div class="flex itemsCentered itemsSpaceAround">
										<div class="pr10">
										<h4>Vertex Vertex Weight </h4>				
												a 1 2 5  <br>
												a 3 4 10  <br>
												a 4 5 6  <br>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>		
		<script>
			 $(document).ready(function() {
				 $("#fileForm").validate({
					 rules: {
						 fileInput: {
					          required: true, 
					        },
						    'dataFormat': {
						      required: true,
						    },
						    'separator': {
						      required: "#matrix:checked",
						    }
						  },
					messages:
				        {
						  fileInput:
				          {
				            required:"Uploading file required"
				          },
				          'dataFormat':
				          {
				            required:"Data format required"
				          },
				          'separator':
				          {
				            required:"Separator required for adjacency matrix"	
				          }
					     },
				   errorPlacement: function(error, element) {
				            if (element.is(":radio")) {
				                error.appendTo(element.parents('#separators'));
					        } else { // This is the default behavior 
					                error.insertAfter( element );
					        } 
				            if (element.is(":radio")) {
					                error.appendTo(element.parents('#dataFormats'));
					        } else { // This is the default behavior 
					                error.insertAfter( element );
					        }
				   },
				});
			 });
		</script>
    </jsp:body>
</t:app>