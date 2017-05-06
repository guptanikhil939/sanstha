<div class="modal fade" id="createEvent" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 700px;">
		<div class="modal-content">
			<div class="modal-header">
				<div>
					<div>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Create an Event</h4>
					</div>
				</div>
			</div>
			<div class="modal-body">
				<form id="createEventForm" method="post">
					<div class="list-group" style="width: 600px;">
						<div id="admin" class="row" style="padding-left: 30px;">
							<div class="row">&nbsp;</div>
							<div class="form-group">
								<div>
									<div class="col-lg-6">
										<input type="text" id="eventName" name="eventName"
											class="form-control" placeholder="Event Name&#42;">
									</div>
									<div class="col-lg-6">
										<input type="text" id="eventLocation" name="eventLocation"
											class="form-control" placeholder="Event Location&#42;">
									</div>
								</div>
								<div class="row">&nbsp;</div>
								<div class="row">&nbsp;</div>
								<div>
									<div class="col-lg-6">
										<div class='input-group date' id='datetimepicker_start'>
											<input type='text' class="form-control" id="eventStartDate"
												name="eventStartDate" /> <span class="input-group-addon">
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class='input-group date' id='datetimepicker_end'>
										<input type='text' class="form-control" id="eventEndDate"
											name="eventEndDate" /> <span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div>
								<div class="col-lg-6">
									<input type="text" id="eventCoordinator"
										name="eventCoordinator" class="form-control"
										placeholder="Event Coordinator&#42;">
								</div>
								<div class="col-lg-6">
									<a class="btn btn-success"
										onclick="saveEvent(${userDTO.societyId})" role="button">Create
										Event</a>
								</div>
							</div>
							<div class="row">&nbsp;</div>
							<div class="row">&nbsp;</div>
							<div class="alert alert-success" id="successEvent">Event
								Created</div>
							<div class="alert alert-danger" id="eventNameNull"
								style="position: relative; text-align: left;">&#42;Please
								enter Event Name</div>
							<div class="alert alert-danger" id="eventLocationNull"
								style="position: relative; text-align: left;">&#42;Please
								enter Event location</div>
							<div class="alert alert-danger" id="eventStartTimeNull"
								style="position: relative; text-align: left;">&#42;Please
								enter Event start Time</div>
							<div class="alert alert-danger" id="eventEndTimeNull"
								style="position: relative; text-align: left;">&#42;Please
								enter Event end Time</div>
							<div class="alert alert-danger" id="eventCoordinatorNameNull"
								style="position: relative; text-align: left;">&#42;Please
								enter Event Coordinator's name</div>
							<div class="alert alert-danger" id="failEvent">Oops! Unable
								to process your request at this moment. It looks like there is
								some problem with your network or your session has expired.
								Please logout and try again.</div>
						</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
						<div class="row">&nbsp;</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>