import React, { useEffect, useState } from "react";
import axios from "axios";

type ShareClass = {
  name: string;
  navDate: number[];
};

type Fund = {
  fundType: string;
  assetType: string;
  currency: string;
  fundName: string;
  price: number;
  shareClass: ShareClass[];
};

const FundList: React.FC = () => {
  const [funds, setFunds] = useState<Fund[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedFund, setSelectedFund] = useState<Fund | null>(null);
  const [quantity, setQuantity] = useState<number>(1);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      alert("No token found. Please login.");
      return;
    }

    axios
      .get<Fund[]>("http://api-gateway:9000/funds", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setFunds(response.data);
      })
      .catch((error) => {
        console.error("Failed to load funds:", error);
        alert("Failed to load funds. Please login again.");
      });
  }, []);

  const handleBuyClick = (fund: Fund) => {
    console.log("Buy clicked for fund:", fund.fundName);
    setSelectedFund(fund);
    setShowModal(true);
  };

  const handleConfirmBuy = () => {
    if (selectedFund) {
      const tradePayload = {
        tradeId: 1, // Replace with unique ID logic if needed
        fundname: selectedFund.fundName,
        quantity: quantity,
        price: selectedFund.price,
        tradeType: "BUY",
      };

      const token = localStorage.getItem("token");
      if (!token) {
        alert("You must be logged in to trade.");
        return;
      }

      axios
        .post("http://api-gateway:9000/trades/processTrades", tradePayload, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })
        .then(() => {
          alert("Trade placed successfully!");
          setShowModal(false);
          setQuantity(1);
        })
        .catch((error) => {
          console.error("Trade failed:", error);
          alert("Trade failed. Try again.");
        });
    }
  };

  return (
    <>
      <div className="p-6">
        <h1 className="text-2xl font-bold mb-4">All Funds</h1>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {funds.map((fund, index) => (
            <div key={index} className="border rounded p-4 shadow-md">
              <h2 className="text-xl font-semibold">{fund.fundName}</h2>
              <p>Fund Type: {fund.fundType}</p>
              <p>Asset Type: {fund.assetType}</p>
              <p>Currency: {fund.currency}</p>
              <p>Price: {fund.price}</p>
              <h3 className="mt-2 font-medium">Share Classes:</h3>
              <ul className="list-disc pl-5">
                {fund.shareClass.map((sc, i) => (
                  <li key={i}>
                    {sc.name} - NAV Date: {sc.navDate.join("-")}
                  </li>
                ))}
              </ul>
              <button
                onClick={() => handleBuyClick(fund)}
                className="mt-4 px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
              >
                Buy
              </button>
            </div>
          ))}
        </div>
      </div>

      {showModal && selectedFund && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-white p-6 rounded shadow-lg w-96">
            <h2 className="text-lg font-semibold mb-4">
              Buy {selectedFund.fundName}
            </h2>
            <input
              type="number"
              min={1}
              value={quantity}
              onChange={(e) => setQuantity(Number(e.target.value))}
              className="border p-2 w-full mb-4"
              placeholder="Enter quantity"
            />
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setShowModal(false)}
                className="px-4 py-2 bg-gray-300 text-black rounded hover:bg-gray-400 transition"
              >
                Cancel
              </button>
              <button
                onClick={handleConfirmBuy}
                className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
              >
                Confirm Buy
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default FundList;
