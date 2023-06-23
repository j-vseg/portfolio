import './datatable.css'
function Pagination({ currentPage, itemsPerPage, totalItems, paginate }) {
  if (currentPage === undefined) {currentPage = 1}
  if (itemsPerPage === undefined) {itemsPerPage = 5}
  if (totalItems === undefined) {totalItems = 0}
  if (paginate === undefined) {paginate = 0}

  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalItems / itemsPerPage); i++) {
    if (!(totalItems <= itemsPerPage)) {pageNumbers.push(i);}
  }
    return ( 
        <nav aria-label="Page navigation example">
        <ul className="pagination justify-content-end">
          {(() => {
          if (pageNumbers.length !== 0) 
            return <li className="page-item">
            <a className="page-link" onClick={() => paginate(currentPage-1)} aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
              <span className="sr-only">Previous</span>
            </a>
          </li>
          })()}
          {pageNumbers.map(number => 
            <li key={number} className="page-item"><a className="page-link" onClick={() => paginate(number)}>{number}</a></li>
            )}
          {(() => {
          if (pageNumbers.length !== 0) 
            return <li className="page-item">
              <a className="page-link" onClick={() => paginate(currentPage+1)} aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span className="sr-only">Next</span>
              </a>
            </li>
          })()}
        </ul>
      </nav>
        );
    }
    
    export default Pagination;