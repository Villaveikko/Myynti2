<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Insert title here</title>
</head>
<body>
<form id="tiedot">
	<table>
		<thead>	
			<tr>
				<th colspan="5" class="oikealle"><span id="takaisin">Takaisin listaukseen</span></th>
			</tr>		
			<tr>
				<th>Etunimi</th>
				<th>Sukunimi</th>
				<th>Sposti</th>
				<th>Puhelin</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" name="etunimi" id="etunimi"></td>
				<td><input type="text" name="sukunimi" id="sukunimi"></td>
				<td><input type="text" name="sposti" id="sposti"></td>
				<td><input type="text" name="puhelin" id="puhelin"></td> 
				<td><input type="submit" id="tallenna" value="Lis��"></td>
			</tr>
		</tbody>
	</table>
</form>
<span id="ilmo"></span>
</body>
<script>
$(document).ready(function(){
	$("#takaisin").click(function(){
		document.location="listaaautot.jsp";
	});
	$("#tiedot").validate({						
		rules: {
			rekNo:  {
				required: true,
				minlength: 3				
			},	
			merkki:  {
				required: true,
				minlength: 2				
			},
			malli:  {
				required: true,
				minlength: 1
			},	
			vuosi:  {
				required: true,
				number: true,
				minlength: 4,
				maxlength: 4,
				min: 1900,
				max: new Date().getFullYear()+1 //Auto voi olla ensivuoden mallia
			}	
		},
		messages: {
			rekNo: {     
				required: "Puuttuu",
				minlength: "Liian lyhyt"			
			},
			merkki: {
				required: "Puuttuu",
				minlength: "Liian lyhyt"
			},
			malli: {
				required: "Puuttuu",
				minlength: "Liian lyhyt"
			},
			vuosi: {
				required: "Puuttuu",
				number: "Ei kelpaa",
				minlength: "Liian lyhyt",
				maxlength: "Liian pitk�",
				min: "Liian pieni",
				max: "Liian suuri"
			}
		},			
		submitHandler: function(form) {	
			lisaaTiedot();
		}		
	}); 	
});
//funktio tietojen lis��mist� varten. Kutsutaan backin POST-metodia ja v�litet��n kutsun mukana uudet tiedot json-stringin�.
//POST /autot/
function lisaaTiedot(){	
	var formJsonStr = formDataJsonStr($("#tiedot").serializeArray()); //muutetaan lomakkeen tiedot json-stringiksi
	$.ajax({url:"autot", data:formJsonStr, type:"POST", dataType:"json", success:function(result) { //result on joko {"response:1"} tai {"response:0"}       
		if(result.response==0){
      	$("#ilmo").html("Auton lis��minen ep�onnistui.");
      }else if(result.response==1){			
      	$("#ilmo").html("Auton lis��minen onnistui.");
      	$("#rekNo", "#merkki", "#malli", "#vuosi").val("");
		}
  }});	
}
</script>
</html>