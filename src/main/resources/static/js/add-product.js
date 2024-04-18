const priceElement = document.getElementById('price');
const tbody = document.getElementById("tbody");


document.getElementById('product-select').addEventListener('change', function(e) {

    let selectedOption = e.target.options[e.target.selectedIndex];

    if (selectedOption.value != 0) {

        let price = selectedOption.getAttribute('data-price');

        priceElement.value = price;
    } else {
        priceElement.value = "0.0";
    }
});



document.getElementById('addProduct').addEventListener('click', e => {

    e.preventDefault();

    const form = document.getElementById("formAddProduct");

    const formData = new FormData(form);

    fillInTheTable(formData);

    $('#modal1').modal('hide')

    form.reset();
});

document.getElementById('sale').addEventListener('click', () => {

    const processProductUrl = document.getElementById('processProductUrl').value;

    const table = document.getElementById('table');
    const sales = [];

    for (let i = 1; i < table.rows.length; i++) {

        let rowData = {};
        let row = table.rows[i];

        rowData.product = row.cells[1].innerHTML;
        rowData.price = row.cells[2].innerHTML;
        rowData.quantity = row.cells[3].innerHTML;
        rowData.total = row.cells[4].innerHTML;
        sales.push(rowData);
    }

    fetch (processProductUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(sales)
    })
    .then(res => {
        if (res.ok) {
            console.log("EXCELLENT");
        } else {
            console.log("SORRY BUT,  THERE IS A ERROR");
        }
    })

    tbody.innerHTML = "";
});



function fillInTheTable(formData) {

    let numberRows = tbody.rows.length;


    let quantityElement = formData.get("quantity");

    if (quantityElement !== 0) {

        let priceElement = formData.get("price");

        let id = `<td> ${numberRows + 1} </td>`;
        let product = `<td> ${formData.get("product")} </td>`;
        let price = `<td> ${priceElement} </td>`;
        let quantity = `<td> ${quantityElement} </td>`;
        let total = `<td> S/. ${priceElement * quantityElement} </td>`;
        let action = `<td> <button type="button" class="btn btn-danger delete">Remove Product</button> </td>`;

        tbody.innerHTML += `<tr>${id + product + price + quantity + total + action}</tr>`;

        addEvents();
    }
}

function addEvents() {

    const deleteButtons = document.querySelectorAll('.delete');

        deleteButtons.forEach(button => {
            button.addEventListener("click", function(e) {

                const row = this.closest('tr');

               row.parentNode.removeChild(row);
            });
        });
}



