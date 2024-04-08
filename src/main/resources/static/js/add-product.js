const priceElement = document.getElementById('price');

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

    const formData = new FormData(document.getElementById("formAddProduct"));

    $('#modal1').modal('hide')
    fillInTheTable(formData)

});

function fillInTheTable(formData) {

    const tbody = document.getElementById("tbody");

    let productForm = formData.get("product");
    let cadenas = productForm.split(" ");

    let id = `<td> ${1} </td>`;
    let product = `<td> ${cadenas[1]} </td>`;
    let price = `<td> ${formData.get("price")} </td>`;
    let quantity = `<td> ${formData.get("quantity")} </td>`;
    let total = `<td> ${formData.get("price") * formData.get("quantity")} </td>`;
    let action = `<td> <button type="button" class="btn btn-danger">Remove Product</button> </td>`;

    tbody.innerHTML += `<tr>${id + product + price + quantity + total + action}</tr>`
}
