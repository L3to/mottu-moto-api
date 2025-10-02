function openDeleteModal(event) {
    event.preventDefault();

    const linkElement = event.target;
    const patioId = linkElement.getAttribute("data-id");

    const modal = document.getElementById("deleteModal");
    modal.style.display = "flex";
    document.getElementById("confirmDelete").onclick = function () {
        window.location.href = `/patios/${patioId}/deletar`;
    };

    document.getElementById("cancelDelete").onclick = function () {
        modal.style.display = "none";
    };
}
