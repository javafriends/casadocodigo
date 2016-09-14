function imprimirDiv() {
			var conteudo = document.getElementById('nome-da-div').innerHTML,
		    tela_impressao = window.open('titulo da pagina');

			tela_impressao.document.write(conteudo);
			tela_impressao.window.print();
			tela_impressao.window.close();
		}

function showModalDialog() {

	$("#modalDialog").dialog(
		{ position: { my: "center center;", at: "center center;", of: window } },
		{ title: "Escolha o tipo da exportação"},
		{ minHeight: 100, minWidth: 500 },
		{ modal: true },
		{ closeText: "Fechar Janela" },
		{ resizable: true }
	);

}

