<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:app>
    <jsp:body>
    	<div class="container p4p">
    		<div class="rowContainer">
				<h2 style="text-align:center;">Welcome to the Graph Partitioning Help Center</h2>
				<p style="text-align: initial; padding: 0 0 0 1%;">The graph partitioning system provides several methods for generation of graph partitions.
				To start enter the following parameters</p>
				<div style="text-align: initial;padding: 0 0 0 1%;">
				<p style="margin:0; padding: 0;font-weight:bold">1. Upload file:</p> 
				<p>The uploaded file should be in .txt format. It should contains graph data. The
				allowed graph data formats are adjacency matrix and list:</p>
				<p  style="margin:0 0 20px 0; padding: 0;font-weight:bold; text-align:center;width:70%">Examples data format</p>	
				<div class="row" style="width:70%;text-align:center;margin-bottom:10px;">
					<div class="col-md-6">
						<p>1. Graph Adjacency matrix</p>
						<div class="row itemsCentered itemsSpaceAround">
							<div class="col-md-6 pr10">
								<p>Space separator</p>				
									0 1 0  <br>
									1 0 1  <br>
									0 1 0  <br>
							</div>
							<div class="col-md-6">
								<p>Comma separator</p>	
									0, 1, 0,  <br>
									1, 0, 1,  <br>
									0, 1, 0,  <br>
							</div>
						</div>
					</div>
					<div class="col-md-6">
					<p>2. Graph List</p>
						<div class="pr10">
							<p>Vertex Vertex Weight </p>				
									a 1 2 5  <br>
									a 3 4 10  <br>
									a 4 5 6  <br>
						</div>
					</div>
				</div>
			<p style="margin:0; padding: 0;font-weight:bold">2. Choose partitioning method:</p> 
			<p>The available partitioning methods are Kernighan-Lin approach, Fiduccia-Mattheyses approach,
			Evolutionary approach and Naive approach</p>
			<p style="margin:0; padding: 0;font-weight:bold">3. Get the results:</p> 
			<p>The generated partitions result can be further used. The image of the original graph 
			and the generated partitions can be downloaded through the "Download image" button in the bottom
			of each image. The data from this graph partitioning can be also exported through the "Export results"
			button in archive, containing three json files(original graph and the generated partitions).</p>					
		</div>
		</div>
		</div>
    </jsp:body>
</t:app>



