function getForm(form, id) {
    var form = document.getElementById(form);
    form.action = form.action+id;
    form.submit();
}

function show_modal() {
    var elem = document.querySelector('.modal');
    var instance = M.Modal.init(elem);
    instance.open();
}

function filterTable() {
    var input, filter, table, tr, td, i, content;
    input = document.getElementById("text");
    filter = input.value.toUpperCase();
    table = document.getElementById("table");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            content = td.textContent || td.innerText;
            if (content.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
