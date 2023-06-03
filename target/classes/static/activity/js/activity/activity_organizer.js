// 接收報名的會員資訊
function memberList() {
  let tbody = document.querySelector("tbody");
  console.log(tbody);
  let activityId = sessionStorage.getItem("activityId");
  fetch(`memberList/${activityId}`)
    .then((res) => {
      return res.json();
    })
    .then((result) => {
      console.log(result);
      for (reser of result) {
        tbody.innerHTML += `
        <tr data-accId=${reser.accId}>
          <td class="align-middle">${reser.accountVO.accName}</td>
          <td class="align-middle">${reser.accountVO.accNickname}</td>
          <td class="align-middle">${reser.accountVO.accAdd1.slice(0, 3)}</td>
          <td class="align-middle">${reser.groupNote}</td>
          <td class="align-middle">
            <button class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#${
              reser.accId
            }Modal" unsigned">
              踢出活動
            </button>
            <!--審核彈窗-->
            <div
              class="modal fade"
              id="${reser.accId}Modal"
              tabindex="-1"
              aria-labelledby="exampleModalLabel"
              aria-hidden="true"
            >
              <div class="modal-dialog w-75 h-auto">
                <div class="modal-content">
                  <div class="modal-body mb-5 m">
                    <h3
                      class="modal-title text-center mt-5"
                      id="exampleModalLabel"
                    >
                      確認踢出?
                    </h3>
                  </div>
                  <div class="row justify-content-around mt-5 pb-3">
                    <button
                      type="button"
                      class="btn btn-outline-danger w-25"
                      data-bs-dismiss="modal"
                    >
                      取消
                    </button>
                    <button
                      type="button"
                      class="btn btn-outline-success w-25 check"
                    >
                      確認
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </td>
          
        </tr>
        `;
      }
      remove();
    });
}

// 踢掉報名會員
function remove() {
  let check_btn = $("button.check");
  check_btn.click(function (e) {
    let accId = $(e.target).closest("tr").attr("data-accId");
    //點擊「確認」將資料庫中的資料刪除
    fetch(`deleteMember/${accId}`, {
      method: "DELETE",
    })
      .then((res) => {
        return res.json();
      })
      .then((result) => {
        console.log(result);
        if (result) {
          location.reload();
        }
      });
  });
}

// 搜尋
function memberSearch() {}

$(function () {
  memberList();
});
