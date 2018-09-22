import React from 'react';
import '../style/VehicleForm.css';

export default class VehicleForm extends React.Component{

	state = {
		brand: '',
		name: '',
		type: '',
		producedYear: '',
		transmission: '',
		color: '',
		location: ''
	};

	change = e => {
		this.setState({
			[e.target.name]: e.target.value
		});
	};

	onSubmit = (e) => {
		e.preventDefault();
		this.props.onSubmit(this.state);
		this.setState({
			brand: '',
			name: '',
			type: '',
			producedYear: '',
			transmission: '',
			color: '',
			location: ''
		})
	};


	render(){

		return (

			<form>
				<div>
					<h3> Add vehicle </h3>
					<input 
						name = "brand"
						placeholder = "Brand" 
						type = "text"
						value = {this.state.brand} 
						onChange={e => this.change(e)}
					/>
					<br />
					<input 
						name = "name"
						placeholder = "Name" 
						value = {this.state.name} 
						onChange={e => this.change(e)}
					/>
					<br />
					<input 
						name = "type"
						placeholder = "Type" 
						value = {this.state.type} 
						onChange={e => this.change(e)}
					/>
					<br />
					<input 
						name = "producedYear"
						placeholder = "Produced year" 
						value = {this.state.producedYear} 
						onChange={e => this.change(e)}
					/>
					<br />
					<input 
						name = "transmission"
						placeholder = "Transmission" 
						value = {this.state.transmission} 
						onChange={e => this.change(e)}
					/>
					<br />
					<input 
						name = "color"
						placeholder = "Color" 
						value = {this.state.color} 
						onChange={e => this.change(e)}
					/>
					<br />
					<input 
						name = "location"
						placeholder = "Location" 
						value = {this.state.location} 
						onChange={e => this.change(e)}
					/>
					<br />
					<button onClick={e => this.onSubmit(e)}>Submit</button>
				</div>
			</form>

		);

	}

	

}