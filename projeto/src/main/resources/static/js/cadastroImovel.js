$(document).ready(function () {
	$('.fotos .btn-floating').click(() => $('#fotos-input').click());
	$('#fotos-input').change(fotoPreview);
});

function fotoPreview(event) {
	
	$('li.preview').remove();
	
	var files = event.originalEvent.target.files;
	
	for(foto of files) {
		var render = new FileReader();
		render.onload = function (ev) {
			let result = ev.target.result;
			$(`<li class="preview"><img src="${result}"></li>`).insertAfter($('.fotos li:first-child'))
		};
		render.readAsDataURL(foto);
	}
}

