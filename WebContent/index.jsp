<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script>
	var valid = false;
	function validate_fileupload(input_element) {
		var el = document.getElementById("feedback");
		var fileName = input_element.value;
		var allowed_extensions = new Array("xml");
		var file_extension = fileName.split('.').pop();
		for (var i = 0; i < allowed_extensions.length; i++) {
			if (allowed_extensions[i] == file_extension) {
				valid = true; // valid file extension
				el.innerHTML = "";
				return;
			}
		}
		el.innerHTML = "Invalid file";
		valid = false;
	}

	function valid_form() {
		return valid;
	}
</script>

</head>
<body>
	<div>
		<h3>Upload Event Log in XML</h3>
		<div id="feedback" style="color: red; size: 150px;"></div>
		<form action="upload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" id="file"  accept="text/xml"
				onchange="validate_fileupload(this);" /> <input type="submit"
				value="upload" onclick="return valid_form();" />
		</form>
	</div>
	<div>
		<h3>Upload Process Log in XML</h3>
		<div id="feedback" style="color: red; size: 150px;"></div>
		<form action="upload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" id="file" accept="text/xml"
				onchange="validate_fileupload(this);" /> <input type="submit"
				value="upload" onclick="return valid_form();" />
		</form>
	</div>
</body>
</body>
</html>