
const atributosSelecionados = new Map();

$(document).ready(function () {
	$('#btn-add-attr').click(adicionarNovoAtributo);
	$('.fotos .btn-floating').click(() => $('#fotos-input').click());
	$('#fotos-input').change(fotoPreview);
});

function novoChipNode(id, qt, label) {
	return $(`
		<div class="chip teal lighten-2 waves-effect waves-light">
			${label}, ${qt}
			<input type="hidden" name="caracteristicas" value="${id}, ${qt}">
			<i class="close material-icons" onclick="removerAtributo(${id})">close</i>
		</div>
	`);
}

function adicionarNovoAtributo() {
	var qt = $('#quantidade-atributo').val();
	var attrId = $('#id-atributo').val();
	
	if (qt.trim() === '' || parseInt(qt) <= 0 || attrId === '' ) {
		return;
	}
	
	if (atributosSelecionados.has(attrId)) {
		return;
	}
	
	var label = $('#id-atributo option[value='+attrId+']').text();
	var newNode = novoChipNode(attrId, qt, label)
	
	atributosSelecionados.set(attrId, {
		'selectOption': $('#id-atributo option[value='+attrId+']'),
		'chipNode': newNode,
		'data': {
			'label': label,
			'quantidade': qt,
		}
	});
	
	newNode.insertBefore($('.chip.add'));
	$('#id-atributo option[value='+attrId+']').attr('disabled', true);
	$('.modal').modal('close');
	$('#quantidade-atributo').val('');
	$('#id-atributo').val('');
}

function removerAtributo(id) {
	let attr = atributosSelecionados.get(String(id));
	attr.selectOption.attr('disabled', false);
	atributosSelecionados.delete(String(id));
}


function fotoPreview(event) {
	var files = event.originalEvent.target.files;
	
	for(foto of files) {
		
		var render = new FileReader();
		render.onload = function (ev) {
			let spanRemove = $('<span><i class="material-icons waves-effect waves-light">remove</i></span>');
			spanRemove.click(() => spanRemove.parent().remove());
			let result = ev.target.result;
			$(`
				<li>
					<img src="${result}">
					<input type="text" name="foto64[]" value="${result}" style="display: none">
				</li>
			`).append(spanRemove).insertAfter($('.fotos li:first-child'))
		};
		render.readAsDataURL(foto);
	}
}

