	<div class="navigation">
	  <a id="home" class='active' href="/GraphPartitioning/">Home</a>
	  <a id="contact" href="/GraphPartitioning/contact">Contact</a>
	  <a id="help" href="/GraphPartitioning/help">Help</a>
	</div>
		<script>
	$(document).ready(function() {
		if(location.pathname.split("/")[2] == 'contact' || location.pathname.split("/")[2] == 'help'){
			$('#home').removeClass('active');
			$('#' + location.pathname.split("/")[2]).addClass('active');
		} else {
			$('#home').addClass('active');
		}
		 
	});
	</script>