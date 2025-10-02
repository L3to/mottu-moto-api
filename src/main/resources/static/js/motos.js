function openDeleteModal(event) {
    event.preventDefault();
    const linkElement = event.target;
    const motoId = linkElement.getAttribute("data-id"); // Fetch the moto ID properly
    const modal = document.getElementById("deleteModal");
    modal.style.display = "flex";

    document.getElementById("confirmDelete").onclick = function () {
        window.location.href = `/motos/${motoId}/deletar`; // Use the ID from data-id
    };

    document.getElementById("cancelDelete").onclick = function () {
        modal.style.display = "none";
    };
}
