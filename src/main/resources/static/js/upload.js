$(document).ready(function() {
	
	var form = document.forms.namedItem("upload");
	$("#submit").click(function(ev) {
	  ev.preventDefault();
  
	  $.ajax({
		  processData: false,
		  contentType: false,
	      enctype: 'multipart/form-data',
		  url: "/upload",
		  method: "POST",
		  data: new FormData(form),
	  	  success: function(response){
	  		  console.log(response);
	  		  $("#file").val("");
	  	  },
	  	  error: function(response){
			  console.log(response);
	  	  }
	  })
	});
})