document.addEventListener('DOMContentLoaded', () => {
    const viewButtons = document.querySelectorAll('.icon-btn.view');
    viewButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            window.location.href = 'compliance_officer_review.html';
        });
    });
});
